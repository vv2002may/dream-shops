package com.projects.dreamShops.exchange.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.projects.dreamShops.model.Cart;

import lombok.Data;

@Data
public class CartResponse {
    private Long cartId;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    // private Set<CartItem> cartItems = Collections.newSetFromMap(new
    // ConcurrentHashMap<>());
    private List<CartItemResponse> cartItems = new ArrayList<>();

    public CartResponse(Cart cart) {
        this.cartId = cart.getId();
        this.totalPrice = cart.getTotalPrice();
        this.cartItems = cart.getCartItems().stream().map(CartItemResponse::new).toList();
    }
}
