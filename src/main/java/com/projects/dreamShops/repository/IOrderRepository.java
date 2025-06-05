package com.projects.dreamShops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.Orders;

public interface IOrderRepository extends JpaRepository<Orders, Long> {

}
