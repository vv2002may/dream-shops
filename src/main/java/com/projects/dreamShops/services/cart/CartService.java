package com.projects.dreamShops.services.cart;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.response.CartResponse;
import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.repository.ICartRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService implements ICartService {

    public final ICartRepository cartRepository;

    @Override
    public CartResponse getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with Id " + id));

        cart.totalAmountUpdate();
        return new CartResponse(cartRepository.save(cart));
    }

    @Override
    public List<CartResponse> getAllCart() {
        List<Cart> cart = cartRepository.findAll();

        return cart.stream().map(CartResponse::new).toList();
    }

    @Override
    public void addCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with Id " + id));
        cartRepository.delete(cart);
    }

    @Override
    public BigDecimal getTotalAmount(Long id) {
        CartResponse cartResponse = getCart(id);
        return cartResponse.getTotalPrice();
    }

}
