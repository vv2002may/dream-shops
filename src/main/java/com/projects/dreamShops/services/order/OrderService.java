package com.projects.dreamShops.services.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.model.Order;
import com.projects.dreamShops.model.OrderItem;
import com.projects.dreamShops.repository.ICartRepository;
import com.projects.dreamShops.repository.IOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final ICartRepository cartRepository;

    @Override
    public Order placeOrder(Long userId) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderItem> createOrderItem(Order order, Cart cart) {
        return null;
    }

}
