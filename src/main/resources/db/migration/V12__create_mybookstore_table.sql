CREATE table if not exists mybookstore
(
    id       uuid primary key,
    book_id  uuid NOT NULL,
    quantity INT  not null,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books (id) on DELETE cascade
)