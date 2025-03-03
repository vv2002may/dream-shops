package com.projects.dreamShops.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.projects.dreamShops.exchange.response.ImageResponse;
import com.projects.dreamShops.model.Image;

public interface IImageService {
    List<ImageResponse> getAllImages();

    Image getImageById(String id);

    void deleteImageById(String id);

    List<ImageResponse> saveImages(List<MultipartFile> file, String productId);

    Image updateImage(MultipartFile file, String id);
}
