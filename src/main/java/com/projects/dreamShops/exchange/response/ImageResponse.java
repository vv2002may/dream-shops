package com.projects.dreamShops.exchange.response;

import com.projects.dreamShops.model.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private Long id;
    private String fileName;
    private String downloadUrl;

    public ImageResponse(Image image) {
        this.id = image.getId();
        this.fileName = image.getFileName();
        this.downloadUrl = image.getDownloadUrl();
    }

}
