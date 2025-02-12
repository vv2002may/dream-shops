package com.projects.dreamShops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.Cart;

public interface ICartRepository extends JpaRepository<Cart, Long> {

}
