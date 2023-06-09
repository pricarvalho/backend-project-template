CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users.user (
    id uuid DEFAULT uuid_generate_v4(),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users.user (id, first_name, last_name, email) VALUES ('574e7d5d-bf46-449d-9307-4f263362dec8','Priscila','Carvalho','priscila.carvalho@example.com');