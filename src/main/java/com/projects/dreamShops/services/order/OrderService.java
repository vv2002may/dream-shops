package com.projects.dreamShops.services.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.enums.OrderStatus;
import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.response.OrderResponse;
import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.model.Orders;
import com.projects.dreamShops.model.Product;
import com.projects.dreamShops.model.OrderItem;
import com.projects.dreamShops.repository.ICartRepository;
import com.projects.dreamShops.repository.IOrderRepository;
import com.projects.dreamShops.repository.IProductRepository;
import com.projects.dreamShops.services.cart.ICartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final ICartRepository cartRepository;
    private final ICartService cartService;
    private final IProductRepository productRepository;

    // @Override
    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("order not found with id " + orderId));
    }

    @Override
    public List<OrderItem> createOrderItem(Orders order, Cart cart) {
        return cart.getCartItems()
                .stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    product.setInventory(product.getInventory() - cartItem.getQuantity());
                    productRepository.save(product);
                    return new OrderItem(
                            cartItem.getQuantity(),
                            cartItem.getUnitPrice(),
                            product,
                            order);
                }).toList();
    }

    public Orders createOrder(Cart cart) {
        Orders order = new Orders();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    public OrderResponse placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);

        if (cart.getCartItems().isEmpty()) {
            throw new ResourceNotFoundException("Cart is empty for user with id " + userId);
        }
        Orders order = createOrder(cart);
        List<OrderItem> orderItems = createOrderItem(order, cart);
        order.setOrderItems(new ArrayList<>(orderItems));
        order.setTotalAmount(calculateTotalAmount(orderItems));
        cartService.clearCart(cart.getId());
        Orders savedOrder = orderRepository.save(order);
        return new OrderResponse(savedOrder);
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
