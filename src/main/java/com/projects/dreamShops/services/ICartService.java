package com.projects.dreamShops.services;

import java.math.BigDecimal;

import com.projects.dreamShops.model.Cart;

public interface ICartService {

    Cart getCart(String id);

    void clearCart(String id);

    BigDecimal getTotalAmount(String id);
}
