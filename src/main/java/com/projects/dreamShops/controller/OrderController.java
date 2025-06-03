package com.projects.dreamShops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.exchange.response.OrderResponse;
import com.projects.dreamShops.model.Order;
import com.projects.dreamShops.services.order.IOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
public class OrderController {

    private final IOrderService orderService;

    // Order placeOrder(Long userId);

    // Order getOrderByUserId(Long userId);

    // List<OrderItem> createOrderItem(Order order, Cart cart);

    @PostMapping("/{userId}")
    ResponseEntity<ApiResponse> createOrder(@PathVariable Long userId) {
        Order order = orderService.placeOrder(userId);
        return ResponseEntity.ok(new ApiResponse("success", new OrderResponse(order)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(new ApiResponse("success", new OrderResponse(order)));

    }
}