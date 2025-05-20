package com.projects.dreamShops.exchange.response;

import java.math.BigDecimal;

import com.projects.dreamShops.model.CartItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemResponse {
    private Long cartItemId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private ProductResponse productResponse;
    private Long cartId;

    public CartItemResponse(CartItem cartItem) {
        this.cartItemId = cartItem.getId();
        this.quantity = cartItem.getQuantity();
        this.unitPrice = cartItem.getUnitPrice();
        this.totalPrice = cartItem.getTotalPrice();
        this.productResponse = new ProductResponse(cartItem.getProduct());
        this.cartId = cartItem.getCart().getId();
    }

}
