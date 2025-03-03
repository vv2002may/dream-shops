package com.projects.dreamShops.services.implementation;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.repository.ICartRepository;
import com.projects.dreamShops.services.ICartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService implements ICartService {

    public final ICartRepository cartRepository;

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        // cart.totalPriceUpdate();

        return cart;
    }

    @Override
    public void clearCart(Long id) {
        cartRepository.deleteAll();
    }

    @Override
    public BigDecimal getTotalAmount(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalPrice();
    }

}
