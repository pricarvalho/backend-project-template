package com.company.app.meetups;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.meetups.Meetup.MeetupCreated;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/meetups")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MeetupResource {

    private final MeetupService service;

    @PostMapping
    public ResponseEntity<MeetupResponse> save(@RequestBody MeetupRequest userRequest) {
        final MeetupCreated meetup = service.create(userRequest);
        return new ResponseEntity<MeetupResponse>(MeetupResponse.from(meetup), CREATED);
    }

    @GetMapping
    public Collection<MeetupResponse> all() {
        final Collection<MeetupCreated> meetups = service.all();
        return meetups.stream()
                .map(MeetupResponse::from)
                .toList();
    }

}
