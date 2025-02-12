package com.projects.dreamShops.services.implementation;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

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
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public void clearCart(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearCart'");
    }

    @Override
    public BigDecimal getTotalAmount(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalAmount'");
    }

}
