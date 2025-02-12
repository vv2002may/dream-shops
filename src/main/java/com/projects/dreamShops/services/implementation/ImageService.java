package com.projects.dreamShops.services.implementation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.response.ImageResponse;
import com.projects.dreamShops.model.Image;
import com.projects.dreamShops.model.Product;
import com.projects.dreamShops.repository.IProductRepository;
import com.projects.dreamShops.repository.ImageRepository;
import com.projects.dreamShops.services.IImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductRepository productRepository;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image Not Found!"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("Image Not Found!");
        });
    }

    @Override
    public List<ImageResponse> saveImages(List<MultipartFile> file, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));

        List<ImageResponse> imageResponses = file.stream().map(f -> {
            try {
                Image image = new Image();
                image.setFileName(f.getOriginalFilename());
                image.setFileType(f.getContentType());
                image.setImage(new SerialBlob(f.getBytes()));
                image.setProduct(product);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl("/api/v1/images/image/download/" + image.getId());
                savedImage = imageRepository.save(image);

                return new ImageResponse(savedImage);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }).collect(Collectors.toList());

        return imageResponses;
    }

    @Override
    public Image updateImage(MultipartFile file, Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image Not Found!"));
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

    @Override
    public List<ImageResponse> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return Image.imageResponses(images);
    }

}
