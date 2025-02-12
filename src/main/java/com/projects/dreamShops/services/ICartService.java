package com.projects.dreamShops.services;

import java.math.BigDecimal;

import com.projects.dreamShops.model.Cart;

public interface ICartService {

    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalAmount(Long id);
}
