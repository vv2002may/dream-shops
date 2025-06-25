package com.projects.dreamShops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.dreamShops.config.security.jwt.JwtUtils;
import com.projects.dreamShops.config.security.user.ShopUserDetails;
import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.request.LoginRequest;
import com.projects.dreamShops.exchange.request.UsersRequest;
import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.exchange.response.JwtResponse;
import com.projects.dreamShops.model.Users;
import com.projects.dreamShops.services.users.IUsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    private final IUsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        usersService.getUserByEmail(loginRequest.getEmail());
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateTokenForUser(authentication);
        ShopUserDetails shopUserDetails = (ShopUserDetails) authentication.getPrincipal();
        JwtResponse jwtResponse = new JwtResponse(shopUserDetails.getId(), jwtToken);

        return ResponseEntity.ok(new ApiResponse("login successful!", jwtResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UsersRequest usersRequest) {

        Users user = usersService.createUsers(usersRequest);
        ShopUserDetails shopUserDetails = ShopUserDetails.buildUserDetails(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(shopUserDetails, null,
                shopUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtUtils.generateTokenForUser(auth);
        JwtResponse jwtResponse = new JwtResponse(shopUserDetails.getId(), jwtToken);
        return ResponseEntity.ok(new ApiResponse("Register successful!", jwtResponse));
    }
}
