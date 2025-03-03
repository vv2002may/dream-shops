package com.projects.dreamShops.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projects.dreamShops.model.Cart;

public interface ICartRepository extends MongoRepository<Cart, String> {

}
