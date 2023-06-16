package com.company.app.meetups;

import static com.company.app.entities.meetups.tables.Meetup.MEETUP;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.app.common.JooqRepository;
import com.company.app.entities.meetups.tables.records.MeetupRecord;
import com.company.app.meetups.Meetup.CreateMeetup;
import com.company.app.meetups.Meetup.UpdateMeetup;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MeetupRepository implements JooqRepository<MeetupRecord> {

    private final DSLContext context;

    @Transactional
    public Optional<Meetup> save(CreateMeetup meetup) {
        return context.insertInto(MEETUP)
                .set(MEETUP.TITLE, meetup.title())
                .set(MEETUP.IMAGE_URL, meetup.image())
                .set(MEETUP.ADDRESS, meetup.address())
                .set(MEETUP.DESCRIPTION, meetup.description())
                .returning()
                .fetchOptional(MeetupRepository::toMeetup);
    }

    public Optional<Meetup> findBy(UUID id) {
        return context.selectFrom(MEETUP).where(MEETUP.ID.eq(id))
                .fetchOptional(MeetupRepository::toMeetup);
    }

    @Transactional
    public boolean delete(UUID id) {
        final int result = context.deleteFrom(MEETUP).where(MEETUP.ID.eq(id)).execute();
        return 1 == result;
    }

    @Transactional
    public Optional<Meetup> update(UUID id, UpdateMeetup meetup) {
        final MeetupRecord record = new MeetupRecord()
                .setTitle(meetup.title())
                .setImageUrl(meetup.image())
                .setAddress(meetup.address())
                .setDescription(meetup.description());

        return context.update(MEETUP)
                .set(optimizeColumnsUpdateOf(record))
                .where(MEETUP.ID.eq(id))
                .returning()
                .fetchOptional(MeetupRepository::toMeetup);
    }

    public Collection<Meetup> all() {
        return context.selectFrom(MEETUP).limit(Integer.valueOf(50))
                .fetch(MeetupRepository::toMeetup);
    }

    private static Meetup toMeetup(MeetupRecord record) {
        return Meetup.builder()
                .id(record.getId())
                .title(record.getTitle())
                .image(record.getImageUrl())
                .address(record.getAddress())
                .description(record.getDescription())
                .build();
    }

}
