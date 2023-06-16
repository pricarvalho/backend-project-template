package com.company.app.meetups;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter()
@SuperBuilder(toBuilder = true)
@Accessors(fluent = true)
@AllArgsConstructor(access = PRIVATE)
public class Meetup {

    @NotNull
    private final String title;
    @NotNull
    private final String image;
    @NotNull
    private final String address;
    @NotNull
    private final String description;

    @Getter()
    @SuperBuilder(toBuilder = true)
    @Accessors(fluent = true)
    public static class MeetupCreated extends Meetup {

        @NotNull
        private final UUID id;
        
        
    }
    
}
