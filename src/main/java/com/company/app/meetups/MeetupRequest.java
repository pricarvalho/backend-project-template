package com.company.app.meetups;

import com.company.app.meetups.Meetup.CreateMeetup;
import com.company.app.meetups.Meetup.UpdateMeetup;

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

    public CreateMeetup toCreateMeetup() {
        return CreateMeetup.builder()
                .title(title)
                .image(image)
                .address(address)
                .description(description)
                .build();
    }

    public UpdateMeetup toUpdateMeetup() {
        return UpdateMeetup.builder()
                .title(title)
                .image(image)
                .address(address)
                .description(description)
                .build();
    }

}
