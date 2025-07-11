package com.projects.dreamShops.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.exchange.response.ImageResponse;
import com.projects.dreamShops.model.Image;
import com.projects.dreamShops.services.image.IImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {

    private final IImageService imageService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllImages() {
        List<ImageResponse> imageResponses = imageService.getAllImages();
        return ResponseEntity.ok(new ApiResponse("Get all images successful", imageResponses));
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(
            @RequestBody List<MultipartFile> files,
            @RequestParam Long productId) {
        List<ImageResponse> imageResponses = imageService.saveImages(files, productId);
        return ResponseEntity.ok(new ApiResponse("Upload successful", imageResponses));
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);

        ByteArrayResource resource = new ByteArrayResource(
                image.getImage().getBytes(1, (int) image.getImage().length()));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }

    @PutMapping("/image/update/{imageId}")
    public ResponseEntity<ApiResponse> updateImage(@RequestBody MultipartFile file, @PathVariable Long imageId) {
        Image image = imageService.updateImage(file, imageId);
        ImageResponse imageResponse = new ImageResponse(image.getId(), image.getFileName(), image.getDownloadUrl());
        return ResponseEntity.ok(new ApiResponse("Update successful", imageResponse));
    }

    @DeleteMapping("/image/delete/{imageId}")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImageById(imageId);
        return ResponseEntity.ok(new ApiResponse("Delete successful", null));
    }

}
