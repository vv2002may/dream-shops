package com.projects.dreamShops.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    private String id;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    @DBRef
    private Product product;

    @DBRef
    private Cart cart;

    public void setTotalPrice() {
        this.totalPrice = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }

}
