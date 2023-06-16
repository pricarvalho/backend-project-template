package com.company.app.meetups;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.common.RestDataResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/meetups")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MeetupResource {

    private final MeetupService service;

    @PostMapping
    public ResponseEntity<RestDataResponse<MeetupResponse>> save(@RequestBody MeetupRequest userRequest) {
        final Meetup meetup = service.create(userRequest);
        return new ResponseEntity<>(
                new RestDataResponse<>(MeetupResponse.from(meetup)), CREATED);
    }

    @GetMapping("/{id}")
    public RestDataResponse<MeetupResponse> get(@PathVariable String id) {
        final Meetup meetup = service.findById(id);
        return new RestDataResponse<>(MeetupResponse.from(meetup));
    }

    @PutMapping("/{id}")
    public RestDataResponse<MeetupResponse> update(@PathVariable String id, @RequestBody MeetupRequest userRequest) {
        final Meetup meetup = service.update(id, userRequest);
        return new RestDataResponse<>(MeetupResponse.from(meetup));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public RestDataResponse<List<MeetupResponse>> all() {
        final Collection<Meetup> meetups = service.all();
        return new RestDataResponse<>(meetups.stream()
                .map(MeetupResponse::from)
                .collect(Collectors.toList()));
    }

}
