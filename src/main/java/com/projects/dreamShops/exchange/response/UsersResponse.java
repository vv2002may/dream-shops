package com.projects.dreamShops.exchange.response;

import java.util.List;

import com.projects.dreamShops.model.Orders;
import com.projects.dreamShops.model.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private Long cartId;
    private List<OrderResponse> orders;

    public UsersResponse(Users user) {
        this.userId = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.cartId = user.getCart().getId();
        this.orders = user.getOrders().stream().map(OrderResponse::new).toList();
    }

}
