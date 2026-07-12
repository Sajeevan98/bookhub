CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,

    uuid UUID NOT NULL UNIQUE,

    order_id BIGINT NOT NULL UNIQUE,

    gateway VARCHAR(30) NOT NULL,

    status VARCHAR(30) NOT NULL,

    amount NUMERIC(10,2) NOT NULL,

    payment_reference VARCHAR(100) NOT NULL UNIQUE,

    gateway_transaction_id VARCHAR(150),

    gateway_status_message VARCHAR(255),

    paid_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_order
        FOREIGN KEY(order_id)
        REFERENCES orders(id)
);