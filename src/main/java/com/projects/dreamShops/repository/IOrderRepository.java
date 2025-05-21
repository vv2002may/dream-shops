package com.projects.dreamShops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
