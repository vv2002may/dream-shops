package com.projects.dreamShops.services.users;

import java.util.List;

import com.projects.dreamShops.exchange.request.UsersRequest;
import com.projects.dreamShops.model.Order;
import com.projects.dreamShops.model.Users;

public interface IUsersService {

    Users getUsersById(Long userId);

    Users createUsers(UsersRequest userRequest);

    Users updateUsers(UsersRequest usersRequest, Long userId);

    void deleteUsers(Long userId);

    List<Order> getAllOrders(Long userId);
}
