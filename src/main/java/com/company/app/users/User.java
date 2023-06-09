package com.company.app.users;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Getter()
@Builder(toBuilder = true)
@Accessors(fluent = true)
@AllArgsConstructor(access = PRIVATE)
public class User {

    private UUID id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Email email;

    @Getter()
    @Builder
    @Accessors(fluent = true)
    @AllArgsConstructor(access = PRIVATE)
    public static class Email {

        @NonNull
        private String value;

    }

}
