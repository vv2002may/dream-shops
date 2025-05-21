package com.projects.dreamShops.services.order;

import java.util.List;

import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.model.Order;
import com.projects.dreamShops.model.OrderItem;

public interface IOrderService {

    Order placeOrder(Long userId);

    Order getOrderById(Long orderId);

    List<OrderItem> createOrderItem(Order order, Cart cart);
}
