package com.projects.dreamShops.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.exchange.response.CartItemResponse;
import com.projects.dreamShops.exchange.response.CartResponse;
import com.projects.dreamShops.services.cart.ICartItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItem")
public class CartItemController {

    private final ICartItemService cartItemService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCartItems() {
        List<CartItemResponse> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(new ApiResponse("Success", cartItems));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCartItem(@RequestParam Long cartId, @RequestParam Long productId,
            @RequestParam int quantity) {
        CartResponse cartResponse = cartItemService.addItemToCart(cartId, productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Success", cartResponse));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> removeCartItem(@RequestParam Long cartId, @RequestParam Long cartItemId) {
        cartItemService.removeItemFromCart(cartId, cartItemId);
        return ResponseEntity.ok(new ApiResponse("Success", null));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateCartItemQuantity(@RequestParam Long cartId, @RequestParam Long cartItemId,
            @RequestParam int quantity) {
        cartItemService.updateItemQuantity(cartId, cartItemId, quantity);
        return ResponseEntity.ok(new ApiResponse("Success", null));
    }
}
