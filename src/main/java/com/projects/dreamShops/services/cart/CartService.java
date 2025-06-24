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
    public CartResponse getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with Id " + cartId));

        cart.totalAmountUpdate();
        return new CartResponse(cartRepository.save(cart));
    }

    @Override
    public List<CartResponse> getAllCart() {
        List<Cart> cart = cartRepository.findAll();
        return cart.stream().map(CartResponse::new).toList();
    }

    @Override
    public Cart addCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with Id " + cartId));
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public BigDecimal getTotalAmount(Long cartId) {
        CartResponse cartResponse = getCart(cartId);
        return cartResponse.getTotalPrice();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with userId " + userId));

        return cart;
    }

}
