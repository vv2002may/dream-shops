package com.projects.dreamShops.exchange.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    Long userId;
    String token;
}
