CREATE TABLE order_items (

    id BIGSERIAL PRIMARY KEY,

    order_id BIGINT NOT NULL,

    book_id BIGINT NOT NULL,

    quantity INTEGER NOT NULL,

    unit_price NUMERIC(10,2) NOT NULL,

    subtotal NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id),

    CONSTRAINT fk_order_items_book
        FOREIGN KEY (book_id)
        REFERENCES books(id)
);