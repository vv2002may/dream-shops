package com.projects.dreamShops.services.cart;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.response.CartItemResponse;
import com.projects.dreamShops.exchange.response.CartResponse;
import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.model.CartItem;
import com.projects.dreamShops.model.Product;
import com.projects.dreamShops.repository.ICartItemRepository;
import com.projects.dreamShops.repository.ICartRepository;
import com.projects.dreamShops.repository.IProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final ICartItemRepository cartItemRepository;
    private final ICartRepository cartRepository;
    private final IProductRepository productRepository;

    @Override
    public CartResponse addItemToCart(Long cartId, Long productId, int quantity) {

        // List<CartItem> cartItems = cartItemRepository.findAll();
        // CartItem cartItem = cartItems.stream()
        // .filter(item -> item.getProduct().getId().equals(productId)
        // && item.getCart().getId().equals(cartId))
        // .findFirst().orElse(new CartItem());

        CartItem cartItem = cartItemRepository.findByProductIdAndCartId(productId, cartId)
                .orElse(new CartItem());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));

        if (cartItem.getId() == null) {
            cartItem.setProduct(product);
            if (product.getInventory() < quantity) {
                cartItem.setQuantity(product.getInventory());
            } else {
                cartItem.setQuantity(quantity);
            }
            cartItem.setCart(cart);
            cartItem.setUnitPrice(product.getPrice());

        } else {
            cartItem.setQuantity(quantity + cartItem.getQuantity());
        }
        cartItem.setTotalPrice();
        cartItemRepository.save(cartItem);
        cart.addCartItem(cartItem);
        cartRepository.save(cart);
        // return new CartItemResponse(cartItem);
        return new CartResponse(cart);

    }

    @Override
    public List<CartItemResponse> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        if (cartItems.size() == 0) {
            throw new ResourceNotFoundException("No Cart Items Found");
        }
        return cartItems.stream().map(CartItemResponse::new).toList();
    }

    @Override
    public CartResponse removeItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));

        CartItem cartItem = cartItemRepository.findByIdAndCartId(cartItemId, cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found"));

        cartItemRepository.delete(cartItem);

        return new CartResponse(cart);
    }

    @Override
    public CartItemResponse updateItemQuantity(Long cartId, Long cartItemId, int quantity) {
        // Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new
        // ResourceNotFoundException("Cart Not Found"));

        CartItem cartItem = cartItemRepository.findByIdAndCartId(cartItemId, cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found!"));

        cartItem.setQuantity(quantity);

        cartItemRepository.save(cartItem);
        return new CartItemResponse(cartItem);
    }

}
