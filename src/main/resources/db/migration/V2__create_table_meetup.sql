CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SCHEMA meetups;

CREATE TABLE IF NOT EXISTS meetups.meetup (
    id uuid DEFAULT uuid_generate_v4(),
    title VARCHAR NOT NULL,
    image_url VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    PRIMARY KEY (id)
);