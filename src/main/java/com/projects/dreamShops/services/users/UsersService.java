package com.projects.dreamShops.services.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceAlreadyExistException;
import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.request.UsersRequest;
import com.projects.dreamShops.model.Order;
import com.projects.dreamShops.model.Users;
import com.projects.dreamShops.repository.IUsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {

    private final IUsersRepository usersRepository;

    @Override
    public Users getUsersById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id " + userId));
    }

    @Override
    public Users createUsers(UsersRequest userRequest) {
        Boolean isUser = usersRepository.existsByEmail(userRequest.getEmail());
        if (isUser) {
            throw new ResourceAlreadyExistException("User already exists with email " + userRequest.getEmail());
        }
        Users user = new Users(userRequest);

        return usersRepository.save(user);
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
    public List<Order> getAllOrders(Long userId) {
        Users user = getUsersById(userId);
        return user.getOrder();
    }

}
