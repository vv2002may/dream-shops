package com.projects.dreamShops.services.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projects.dreamShops.exception.Image.ImageNotFoundException;
import com.projects.dreamShops.exception.product.ProductNotFoundException;
import com.projects.dreamShops.exchange.response.image.ImageResponse;
import com.projects.dreamShops.model.Image;
import com.projects.dreamShops.model.Product;
import com.projects.dreamShops.repository.image.ImageRepository;
import com.projects.dreamShops.repository.product.IProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductRepository productRepository;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image Not Found!"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ImageNotFoundException("Image Not Found!");
        });
    }

    @Override
    public List<ImageResponse> saveImages(List<MultipartFile> file, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));

        List<ImageResponse> imageResponses = file.stream().map(f -> {
            try {
                Image image = new Image();
                image.setFileName(f.getOriginalFilename());
                image.setFileType(f.getContentType());
                image.setImage(new SerialBlob(f.getBytes()));
                image.setProduct(product);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl("/api/images/image/download/" + image.getId());
                savedImage = imageRepository.save(image);

                ImageResponse imageResponse = new ImageResponse();
                imageResponse.setImageId(savedImage.getId());
                imageResponse.setImageName(savedImage.getFileName());
                imageResponse.setDownloadUrl(savedImage.getDownloadUrl());
                return imageResponse;
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }).collect(Collectors.toList());

        return imageResponses;
    }

    @Override
    public Image updateImage(MultipartFile file, Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image Not Found!"));
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            return imageRepository.save(image);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

}
