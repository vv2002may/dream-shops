package com.projects.dreamShops.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projects.dreamShops.model.CartItem;

public interface ICartItemRepository extends MongoRepository<CartItem, String> {

}
