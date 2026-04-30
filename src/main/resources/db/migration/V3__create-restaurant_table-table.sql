CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE restaurant_table (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    capacidade INTEGER NOT NULL,
    status VARCHAR(50)
)