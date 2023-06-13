package com.company.app.users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.common.KeycloakService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserService {

    private final Users users;
    private final KeycloakService keycloakService;

    public User create(UserRequest request) {
        keycloakService.create(request);
        // return users.save(request.toDomain())
        //         .orElseThrow(() -> new IllegalArgumentException("Error to save user"));
        return null;        
    }

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

    public User update(String id, UserRequest request) {
        return Optional.ofNullable(id)
                .map(UUID::fromString)
                .map(uuid -> request.toDomain().toBuilder().id(uuid).build())
                .map(users::update)
                .orElseThrow(() -> new IllegalArgumentException("Error to update user"));
    }

}
