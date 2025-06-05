package com.projects.dreamShops.services.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceAlreadyExistException;
import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.request.UsersRequest;
import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.model.Orders;
import com.projects.dreamShops.model.Users;
import com.projects.dreamShops.repository.ICartRepository;
import com.projects.dreamShops.repository.IUsersRepository;
import com.projects.dreamShops.services.cart.ICartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {

    private final IUsersRepository usersRepository;
    private final ICartService cartService;
    private final ICartRepository cartRepository;

    @Override
    public Users getUsersById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id " + userId));
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users createUsers(UsersRequest usersRequest) {
        Boolean isUser = usersRepository.existsByEmail(usersRequest.getEmail());
        if (isUser) {
            throw new ResourceAlreadyExistException("User already exists with email " + usersRequest.getEmail());
        }
        Users user = new Users(usersRequest);
        Cart cart = cartService.addCart();
        user.setCart(cart);
        cart.setUser(user);
        Users savedUser = usersRepository.save(user);
        cartRepository.save(cart);
        return savedUser;
    }

    @Override
    public Users updateUsers(UsersRequest usersRequest, Long userId) {
        Users user = getUsersById(userId);
        user.setFirstName(usersRequest.getFirstName());
        user.setLastName(user.getLastName());
        usersRepository.save(user);
        return user;

    }

    @Override
    public void deleteUsers(Long userId) {
        Users user = getUsersById(userId);
        usersRepository.delete(user);
    }

    @Override
    public List<Orders> getAllOrders(Long userId) {
        Users user = getUsersById(userId);
        return user.getOrders();
    }

}
