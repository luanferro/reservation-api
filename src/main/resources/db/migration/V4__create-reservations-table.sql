CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE reservations (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id UUID NOT NULL,
    table_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    reserved BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_reservations_user FOREIGN KEY (user_id) REFERENCES usuarios(id),
    CONSTRAINT fk_reservations_table FOREIGN KEY (table_id) REFERENCES restaurant_table(id)
)