package com.company.app.meetups;

import lombok.Data;
import lombok.NonNull;

@Data
public class MeetupRequest {

    @NonNull
    private final String title;
    @NonNull
    private final String image;
    @NonNull
    private final String address;
    @NonNull
    private final String description;

    public Meetup toMeetup() {
        return Meetup.builder()
                .title(title)
                .image(image)
                .address(address)
                .description(description)
                .build();
    }

}
