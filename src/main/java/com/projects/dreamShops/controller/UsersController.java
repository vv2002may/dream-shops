package com.projects.dreamShops.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.dreamShops.exchange.request.UsersRequest;
import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.exchange.response.CartResponse;
import com.projects.dreamShops.exchange.response.UsersResponse;
import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.model.Users;
import com.projects.dreamShops.services.users.IUsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/user")
public class UsersController {

    private final IUsersService usersService;

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UsersRequest usersRequest) {
        Users user = usersService.createUsers(usersRequest);
        return ResponseEntity.ok(new ApiResponse("success", new UsersResponse(user)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<Users> users = usersService.getAllUsers();

        List<UsersResponse> usersResponse = users.stream()
                .map(UsersResponse::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse("success", usersResponse));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        Users user = usersService.getUserById(userId);
        return ResponseEntity.ok(new ApiResponse("success", new UsersResponse(user)));
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<ApiResponse> getCartByUserId(@PathVariable Long userId) {
        Cart cart = usersService.getCartByUserId(userId);

        return ResponseEntity.ok(new ApiResponse("success", new CartResponse(cart)));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UsersRequest usersRequest, @PathVariable Long userId) {
        Users user = usersService.updateUsers(usersRequest, userId);
        return ResponseEntity.ok(new ApiResponse("success", new UsersResponse(user)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        usersService.deleteUsers(userId);
        return ResponseEntity.ok(new ApiResponse("success", null));
    }
}
