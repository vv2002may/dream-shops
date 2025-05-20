package com.projects.dreamShops.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<CartItem> cartItems = new HashSet<>();
    private List<CartItem> cartItems = new ArrayList<>();

    // public List<CartItem> getCartItems() {
    // return new ArrayList<>(cartItems);
    // }

    public void totalPriceUpdate() {
        this.totalPrice = cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        totalPriceUpdate();
    }

    public void removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
        totalPriceUpdate();
    }
}