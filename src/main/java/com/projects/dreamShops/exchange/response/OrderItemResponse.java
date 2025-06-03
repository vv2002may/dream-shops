package com.projects.dreamShops.exchange.response;

import java.math.BigDecimal;

import com.projects.dreamShops.model.OrderItem;
import com.projects.dreamShops.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemResponse {
    private Long orderItemId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Long productId;
    private Long orderId;

    public OrderItemResponse(OrderItem orderItem) {
        this.orderItemId = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getUnitPrice();
        this.totalPrice = orderItem.getTotalPrice();
        this.productId = orderItem.getProduct().getId();
        this.orderId = orderItem.getOrder().getId();

    }
}
