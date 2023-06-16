package com.company.app.meetups;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MeetupService {

	private final MeetupRepository meetups;

	public Meetup create(MeetupRequest request) {
		return Optional.ofNullable(request)
				.map(MeetupRequest::toCreateMeetup)
				.flatMap(meetups::save)
				.orElseThrow(() -> new IllegalArgumentException("Error to save Meetup"));
	}

	public Meetup findById(String id) {
		return Optional.ofNullable(id)
				.map(UUID::fromString)
				.flatMap(meetups::findBy)
				.orElseThrow(() -> new IllegalArgumentException("Meetup not found"));
	}

	public boolean delete(String id) {
		return Optional.ofNullable(id)
				.map(UUID::fromString)
				.map(meetups::delete)
				.orElseThrow(() -> new IllegalArgumentException("Error to delete Meetup"));
	}

	public Meetup update(String id, MeetupRequest request) {
		return Optional.ofNullable(id)
				.filter(uuid -> Objects.nonNull(request))
				.map(UUID::fromString)
				.flatMap(uuid -> meetups.update(uuid, request.toUpdateMeetup()))
				.orElseThrow(() -> new IllegalArgumentException("Error to update Meetup"));
	}

	public Collection<Meetup> all() {
		return meetups.all();
	}

}
