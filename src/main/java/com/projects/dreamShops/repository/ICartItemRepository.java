package com.projects.dreamShops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.CartItem;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {

}
