package com.company.app.users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserService {

    private final UserRepository users;

    public User findById(String id) {
        return Optional.ofNullable(id)
                .map(UUID::fromString)
                .flatMap(users::findBy)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public boolean delete(String id) {
        return Optional.ofNullable(id)
                .map(UUID::fromString)
                .map(users::delete)
                .orElseThrow(() -> new IllegalArgumentException("Error to delete user"));
    }

    public User save(UserRequest request) {
        return Optional.ofNullable(request)
                .map( x -> request.toCreateUser())
                .flatMap(users::save)
                .orElseThrow(() -> new IllegalArgumentException("Error to save user"));
    }
}
