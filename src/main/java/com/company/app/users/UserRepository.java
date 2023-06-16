package com.company.app.users;

import static com.company.app.entities.users.tables.User.USER;

import java.util.Optional;
import java.util.UUID;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.app.common.JooqRepository;
import com.company.app.entities.users.tables.records.UserRecord;
import com.company.app.users.User.CreateUser;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserRepository implements JooqRepository<UserRecord> {

    private final DSLContext context;

    public Optional<User> findBy(UUID id) {
        return context.selectFrom(USER).where(USER.ID.eq(id))
                .fetchOptional()
                .map(UserRepository::toDomain);
    }

    @Transactional
    public Optional<User> save(CreateUser user) {
        return context.insertInto(USER)
                .set(USER.FIRST_NAME, user.firstName())
                .set(USER.LAST_NAME, user.lastName())
                .set(USER.EMAIL, user.email())
                .returning()
                .fetchOptional(UserRepository::toDomain);
    }

    public boolean delete(UUID id) {
        val result = context.deleteFrom(USER).where(USER.ID.eq(id)).execute();
        return 1 == result;
    }

    private static User toDomain(UserRecord record) {
        return User.builder()
                        .id(record.getId())
                        .firstName(record.getFirstName())
                        .lastName(record.getLastName())
                        .email(record.getEmail())
                        .build();
    }

}
