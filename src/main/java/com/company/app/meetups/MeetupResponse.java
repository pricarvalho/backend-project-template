package com.company.app.meetups;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MeetupResponse {

	@NonNull
	private final String id;
	@NonNull
	private final String title;
	@NonNull
	private final String image;
	@NonNull
	private final String address;
	@NonNull
	private final String description;

	public static MeetupResponse from(Meetup meetup) {
		return MeetupResponse.builder()
				.id(meetup.id().toString())
				.title(meetup.title())
				.image(meetup.image())
				.address(meetup.address())
				.description(meetup.description())
				.build();
	}

}
