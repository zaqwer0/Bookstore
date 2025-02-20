CREATE table if not exists users
(
    id                UUID                                      NOT NULL PRIMARY KEY,
    username          VARCHAR(255)                              NOT NULL UNIQUE,
    password          VARCHAR(255)                              NOT NULL,
    email             VARCHAR(255)                              NOT NULL UNIQUE,
    registration_date TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL
);
