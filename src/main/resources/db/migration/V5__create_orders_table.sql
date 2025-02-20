CREATE table if not exists orders
(
    id           UUID                                      NOT NULL PRIMARY KEY,
    user_id      UUID                                      NOT NULL,
    book_id      UUID                                      NOT NULL,
    quantity     BIGINT                                    NOT NULL,
    order_date   TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    order_status VARCHAR(50)                               NOT NULL,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_orders_book FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE
);
