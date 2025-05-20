package com.projects.dreamShops.exchange.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;

    // public ApiResponse(String messsage) {
    // this.message = messsage;
    // }
}
