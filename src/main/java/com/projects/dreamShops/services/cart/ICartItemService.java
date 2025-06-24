package com.projects.dreamShops.services.cart;

import java.util.List;

import com.projects.dreamShops.exchange.response.CartItemResponse;
import com.projects.dreamShops.exchange.response.CartResponse;

public interface ICartItemService {
    CartResponse addItemToCart(Long cartId, Long productId, int quantity);

    List<CartItemResponse> getAllCartItems();

    CartResponse removeItemFromCart(Long cartId, Long cartItemId);

    CartItemResponse updateItemQuantity(Long cartId, Long cartItemId, int quantity);
}
