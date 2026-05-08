CREATE TABLE reservations (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    table_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_reservations_user FOREIGN KEY (user_id) REFERENCES usuarios(id),
    CONSTRAINT fk_reservations_table FOREIGN KEY (table_id) REFERENCES restaurant_table(id)
)