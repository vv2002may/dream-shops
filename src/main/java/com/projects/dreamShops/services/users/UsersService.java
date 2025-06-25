package com.projects.dreamShops.services.users;

import java.util.List;
import java.util.Set;

import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceAlreadyExistException;
import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.request.UsersRequest;
import com.projects.dreamShops.model.Cart;
import com.projects.dreamShops.model.Orders;
import com.projects.dreamShops.model.Role;
import com.projects.dreamShops.model.Users;
import com.projects.dreamShops.repository.ICartRepository;
import com.projects.dreamShops.repository.IRoleRepository;
import com.projects.dreamShops.repository.IUsersRepository;
import com.projects.dreamShops.services.cart.ICartService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {

    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;

    private final IUsersRepository usersRepository;
    private final ICartService cartService;
    private final ICartRepository cartRepository;

    @Override
    public Users getUserById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id " + userId));
    }

    @Override
    public void getUserByEmail(String email) {
        if (!usersRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("User not exist with email " + email + ". Please register first.");
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return getUserById(userId).getCart();
    }

    @Override
    public Users createUsers(UsersRequest usersRequest) {
        Boolean isUser = usersRepository.existsByEmail(usersRequest.getEmail());
        if (isUser) {
            throw new ResourceAlreadyExistException("User already exists with email " + usersRequest.getEmail());
        }
        usersRequest.setPassword(passwordEncoder.encode(usersRequest.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER").get();
        Users user = new Users(usersRequest);
        Cart cart = cartService.addCart();
        user.setCart(cart);
        user.setRoles(Set.of(userRole));
        cart.setUser(user);
        Users savedUser = usersRepository.save(user);
        return savedUser;
    }

    @Override
    public Users updateUsers(UsersRequest usersRequest, Long userId) {
        Users user = getUserById(userId);
        user.setFirstName(usersRequest.getFirstName());
        user.setLastName(usersRequest.getLastName());
        usersRepository.save(user);
        return user;

    }

    @Override
    public void deleteUsers(Long userId) {
        Users user = getUserById(userId);
        usersRepository.delete(user);
    }

    @Override
    public List<Orders> getAllOrders(Long userId) {
        Users user = getUserById(userId);
        return user.getOrders();
    }

}
