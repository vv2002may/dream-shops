package com.projects.dreamShops.services;

import java.math.BigDecimal;
import java.util.List;

import com.projects.dreamShops.exchange.response.CartResponse;
import com.projects.dreamShops.model.Cart;

public interface ICartService {

    void addCart();

    List<CartResponse> getAllCart();

    CartResponse getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalAmount(Long id);
}
