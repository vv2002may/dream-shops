package com.projects.dreamShops.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.Users;

public interface IUsersRepository extends JpaRepository<Users, Long> {

    Boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

}
