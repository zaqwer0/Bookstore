CREATE table if not exists authors (
    id        UUID PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
)