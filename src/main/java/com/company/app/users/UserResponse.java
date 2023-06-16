package com.company.app.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class UserResponse {
    
    @NonNull
    private String id; 
    @NonNull
    private String firstName;
    @NonNull 
    private String lastName;
    @NonNull 
    private String email;

    public static UserResponse from(User user) {
        return UserResponse.builder()
        .id(user.id().toString())
        .firstName(user.firstName())
        .lastName(user.lastName())
        .email(user.email())
        .build() ;
    }

}