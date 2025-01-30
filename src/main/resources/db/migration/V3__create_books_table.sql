CREATE table if not exists books (
    id             UUID PRIMARY KEY,
    title          VARCHAR(255)   NOT NULL,
    author_id      UUID           NOT NULL,
    price          NUMERIC(10, 2) NOT NULL,
    published_year INT            NOT NULL,
    bookstore_id   UUID           NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE,
    CONSTRAINT fk_bookstore FOREIGN KEY (bookstore_id) REFERENCES bookstores (id) ON DELETE CASCADE
);
