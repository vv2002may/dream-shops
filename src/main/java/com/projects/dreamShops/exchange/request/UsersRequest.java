package com.projects.dreamShops.exchange.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersRequest {
    private String firstName;
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    private String password;
}
