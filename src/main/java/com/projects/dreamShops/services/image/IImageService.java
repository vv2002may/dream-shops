package com.projects.dreamShops.services.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.projects.dreamShops.exchange.response.image.ImageResponse;
import com.projects.dreamShops.model.Image;
import com.projects.dreamShops.model.Product;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageResponse> saveImages(List<MultipartFile> file, Long productId);
    Image updateImage(MultipartFile file, Long id);
}
