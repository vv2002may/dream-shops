package com.projects.dreamShops.services.cart;

import java.math.BigDecimal;
import java.util.List;

import com.projects.dreamShops.exchange.response.CartResponse;
import com.projects.dreamShops.model.Cart;

public interface ICartService {

    Cart addCart();

    List<CartResponse> getAllCart();

    CartResponse getCart(Long cartId);

    void clearCart(Long cartId);

    BigDecimal getTotalAmount(Long cartId);

    Cart getCartByUserId(Long userId);
}
