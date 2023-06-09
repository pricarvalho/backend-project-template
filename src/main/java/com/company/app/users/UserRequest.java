package com.company.app.users;

import com.company.app.users.User.Email;

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

    public User toDomain() {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(Email.builder().value(email).build())
                .build();
    }

}
