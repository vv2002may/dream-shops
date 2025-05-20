package com.projects.dreamShops.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.exchange.response.CartResponse;
import com.projects.dreamShops.services.ICartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cart")
public class CartController {

    private final ICartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponse> addCart() {
        cartService.addCart();
        return ResponseEntity.ok(new ApiResponse("Success", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCarts() {
        try {
            List<CartResponse> cartResponses = cartService.getAllCart();
            return ResponseEntity.ok(new ApiResponse("Success", cartResponses));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
        CartResponse cartResponse = cartService.getCart(cartId);
        return ResponseEntity.ok(new ApiResponse("Success", cartResponse));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);

        return ResponseEntity.ok(new ApiResponse("Success", null));
    }
}
