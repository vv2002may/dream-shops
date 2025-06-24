package com.projects.dreamShops.config.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.dreamShops.config.security.user.ShopUserDetailsService;
import com.projects.dreamShops.exception.ResourceNotFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ShopUserDetailsService shopUserDetailsService;

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);

            if (StringUtils.hasText(jwt) && jwtUtils.validateToken(jwt)) {
                String email = jwtUtils.getUsernameFromToken(jwt);
                UserDetails userDetails = shopUserDetailsService.loadUserByUsername(email);
                var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (JwtException e) {

            // jwtAuthEntryPoint.commence(request, response, new AuthenticationException(
            // e.getMessage() + "Invalid or expired token. You may login and try again.") {
            // });

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("timestamp", LocalDateTime.now().toString());
            errorBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            errorBody.put("error", "Unauthorized");

            // Friendly message for end users
            if (e instanceof ExpiredJwtException) {
                errorBody.put("message", "Your session has expired. Please log in again.");
            } else {
                errorBody.put("message", "Invalid or expired token. You may login and try again.");
            }
            errorBody.put("exception", e.getClass().getSimpleName());

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), errorBody);
            return;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(e.getMessage());
            return;

        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {

        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
