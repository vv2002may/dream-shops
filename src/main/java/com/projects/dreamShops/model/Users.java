package com.projects.dreamShops.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.UniqueElements;

import com.projects.dreamShops.exchange.request.UsersRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @NaturalId
    @UniqueElements
    private String email;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> order;

    public Users(UsersRequest usersRequest) {
        this.firstName = usersRequest.getFirstName();
        this.lastName = usersRequest.getLastName();
        this.email = usersRequest.getEmail();
        this.password = usersRequest.getPassword();
        this.cart = null;
        this.order = new ArrayList<>();
    }
}
