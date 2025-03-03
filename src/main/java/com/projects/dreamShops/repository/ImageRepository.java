package com.projects.dreamShops.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projects.dreamShops.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {

}
