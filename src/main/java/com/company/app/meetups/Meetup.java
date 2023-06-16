package com.company.app.meetups;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter()
@Builder(toBuilder = true)
@Accessors(fluent = true)
@AllArgsConstructor(access = PRIVATE)
public class Meetup {

    @NotNull
    private final UUID id;
    
    @NotNull
    private final String title;
    @NotNull
    private final String image;
    @NotNull
    private final String address;
    @NotNull
    private final String description;

    @Getter()
    @Builder(toBuilder = true)
    @Accessors(fluent = true)
    @AllArgsConstructor(access = PRIVATE)
    public static class CreateMeetup {

        @NotNull
        private final String title;
        @NotNull
        private final String image;
        @NotNull
        private final String address;
        @NotNull
        private final String description;
        
    }

    @Getter()
    @Builder(toBuilder = true)
    @Accessors(fluent = true)
    @AllArgsConstructor(access = PRIVATE)
    public static class UpdateMeetup {

        @NotNull
        private final String title;
        @NotNull
        private final String image;
        @NotNull
        private final String address;
        @NotNull
        private final String description;
        
    }
    
}
