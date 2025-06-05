package com.projects.dreamShops.services.users;

import java.util.List;

import com.projects.dreamShops.exchange.request.UsersRequest;
import com.projects.dreamShops.model.Orders;
import com.projects.dreamShops.model.Users;

public interface IUsersService {

    Users getUsersById(Long userId);

    List<Users> getAllUsers();

    Users createUsers(UsersRequest userRequest);

    Users updateUsers(UsersRequest usersRequest, Long userId);

    void deleteUsers(Long userId);

    List<Orders> getAllOrders(Long userId);
}
