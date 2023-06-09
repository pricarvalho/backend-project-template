package com.company.app.users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final Users users;

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
        return users.save(request.toDomain())
                .orElseThrow(() -> new IllegalArgumentException("Error to save user"));
    }

    public User update(String id, UserRequest request) {
        return Optional.ofNullable(id)
                .map(UUID::fromString)
                .map(uuid -> request.toDomain().toBuilder().id(uuid).build())
                .map(users::update)
                .orElseThrow(() -> new IllegalArgumentException("Error to update user"));
    }

}
