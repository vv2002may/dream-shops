package com.projects.dreamShops.exchange.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.projects.dreamShops.enums.OrderStatus;
import com.projects.dreamShops.model.Orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private List<OrderItemResponse> orderItems;
    private Long userId;

    public OrderResponse(Orders order) {
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate();
        this.totalAmount = order.getTotalAmount();
        this.orderStatus = order.getOrderStatus();
        this.orderItems = order.getOrderItems().stream().map(OrderItemResponse::new).toList();
        this.userId = order.getUser().getId();
    }

}
