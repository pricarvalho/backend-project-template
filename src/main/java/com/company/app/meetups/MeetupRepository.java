package com.company.app.meetups;

import static com.company.app.entities.meetups.tables.Meetup.MEETUP;

import java.util.Collection;
import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.app.common.JooqRepository;
import com.company.app.entities.meetups.tables.records.MeetupRecord;
import com.company.app.meetups.Meetup.MeetupCreated;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MeetupRepository implements JooqRepository<MeetupRecord> {

    private final DSLContext context;

    @Transactional
    public Optional<MeetupCreated> save(Meetup meetup) {
        return context.insertInto(MEETUP)
                .set(MEETUP.TITLE, meetup.title())
                .set(MEETUP.IMAGE_URL, meetup.image())
                .set(MEETUP.ADDRESS, meetup.address())
                .set(MEETUP.DESCRIPTION, meetup.description())
                .returning()
                .fetchOptional(record -> MeetupCreated.builder()
                        .id(record.getId())
                        .title(record.getTitle())
                        .image(record.getImageUrl())
                        .address(record.getAddress())
                        .description(record.getDescription())
                        .build());
    }

    public Collection<MeetupCreated> all() {
        return context.selectFrom(MEETUP).limit(Integer.valueOf(50))
                .fetch(record -> MeetupCreated.builder()
                        .id(record.getId())
                        .title(record.getTitle())
                        .image(record.getImageUrl())
                        .address(record.getAddress())
                        .description(record.getDescription())
                        .build());
    }

}
