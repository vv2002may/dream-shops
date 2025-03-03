package com.projects.dreamShops.model;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    private String id;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @DBRef
    private Set<CartItem> cartItems;

    public void totalPriceUpdate() {
        this.totalPrice = cartItems.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
