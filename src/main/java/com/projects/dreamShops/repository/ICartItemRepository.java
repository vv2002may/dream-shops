package com.projects.dreamShops.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.CartItem;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByIdAndCartId(Long cartItemId, Long cartid);

}
