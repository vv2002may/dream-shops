package com.projects.dreamShops.services.order;

import java.util.List;

import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.model.Orders;
import com.projects.dreamShops.model.OrderItem;

public interface IOrderService {

    Orders placeOrder(Long userId);

    Orders getOrderById(Long orderId);

    List<OrderItem> createOrderItem(Orders order, Cart cart);

}
