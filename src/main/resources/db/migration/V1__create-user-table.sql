CREATE EXTENSION IF NOT EXISTS "citext";

CREATE TABLE usuarios (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email CITEXT NOT NULL CHECK (email <> ''),
    senha VARCHAR(150) NOT NULL,
    role VARCHAR(50) NOT NULL,
    CONSTRAINT uk_usuarios_email UNIQUE (email)
)