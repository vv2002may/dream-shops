package com.projects.dreamShops.model;

import java.sql.Blob;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.projects.dreamShops.exchange.response.ImageResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Image {
    @Id
    private String id;
    private String fileName;
    private String fileType;

    private byte[] image;
    private String downloadUrl;

    @DBRef
    private Product product;

    public static List<ImageResponse> imageResponses(List<Image> images) {
        return images.stream().map(ImageResponse::new).collect(Collectors.toList());
    }
}
