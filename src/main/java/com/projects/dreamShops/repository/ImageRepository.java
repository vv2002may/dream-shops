package com.projects.dreamShops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
