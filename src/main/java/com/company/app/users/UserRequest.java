package com.company.app.users;

import com.company.app.users.User.CreateUser;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequest {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;

    public CreateUser toCreateUser() {
        return CreateUser.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }

}
