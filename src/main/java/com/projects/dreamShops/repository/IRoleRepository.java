package com.projects.dreamShops.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String string);

}
