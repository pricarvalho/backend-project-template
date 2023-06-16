package com.company.app.meetups;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.meetups.Meetup.MeetupCreated;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MeetupService {

    private final MeetupRepository meetups;

    public MeetupCreated create(MeetupRequest request) {
        return meetups.save(request.toMeetup())
                .orElseThrow(() -> new IllegalArgumentException("Error to save user"));
    }

	public Collection<MeetupCreated> all() {
		return meetups.all();
	}

}
