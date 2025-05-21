package com.projects.dreamShops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.OrderItem;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {

}
