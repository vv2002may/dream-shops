package com.projects.dreamShops.exchange.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank
    String email;
    @NotBlank
    String password;
}
