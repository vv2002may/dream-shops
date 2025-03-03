package com.projects.dreamShops.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Category {

    @Id
    private String id;
    private String name;

    @DBRef
    private List<Product> products;

    public Category(String name) {
        this.name = name;
    }
}
