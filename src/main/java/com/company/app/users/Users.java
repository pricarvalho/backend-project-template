package com.company.app.users;

import static com.company.app.persistence.tables.User.USER;

import java.util.Optional;
import java.util.UUID;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.app.common.JooqRepository;
import com.company.app.persistence.tables.records.UserRecord;
import com.company.app.users.User.Email;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
@RequiredArgsConstructor(onConstructor_= {@Autowired})
public class Users implements JooqRepository<UserRecord> {

    private final DSLContext context;

    public Optional<User> findBy(UUID id) {
        return context.selectFrom(USER).where(USER.ID.eq(id))
                .fetchOptional()
                .map(record -> User.builder()
                        .id(record.getId())
                        .firstName(record.getFirstName())
                        .lastName(record.getLastName())
                        .email(Email.builder().value(record.getEmail()).build())
                        .build());
    }

    @Transactional
    public Optional<User> save(User user) {
        return context.insertInto(USER)
                .set(USER.FIRST_NAME, user.firstName())
                .set(USER.LAST_NAME, user.lastName())
                .set(USER.EMAIL, user.email().value())
                .returning()
                .fetchOptional(record -> User.builder()
                        .id(record.getId())
                        .firstName(record.getFirstName())
                        .lastName(record.getLastName())
                        .email(Email.builder().value(record.getEmail()).build())
                        .build());
    }

    @Transactional
    public User update(User user) {
        val record = context.selectFrom(USER).where(USER.ID.eq(user.id())).fetchOne();
        record.setFirstName(user.firstName());
        record.setLastName(user.lastName());
        record.setEmail(user.email().value());

        return context.update(USER)
                .set(optimizeColumnsUpdateOf(record))
                .where(USER.ID.eq(user.id()))
                .returning()
                .fetchOptional(updatedRecord -> User.builder()
                        .id(updatedRecord.getId())
                        .firstName(updatedRecord.getFirstName())
                        .lastName(updatedRecord.getLastName())
                        .email(Email.builder().value(updatedRecord.getEmail()).build())
                        .build())
                        .orElse(user);
    }

    public boolean delete(UUID id) {
        val result = context.deleteFrom(USER).where(USER.ID.eq(id)).execute();
        return 1 == result;
    }

}
