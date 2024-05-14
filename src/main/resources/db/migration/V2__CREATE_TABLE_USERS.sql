CREATE TABLE users (
    id BIGSERIAL,
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),

    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,

    address_street VARCHAR(100),
    address_number VARCHAR(10),
    address_complement VARCHAR(50),
    address_neighborhood VARCHAR(50),
    address_city VARCHAR(50),
    address_state VARCHAR(20),
    address_cep VARCHAR(20),

    account_type VARCHAR(10),

    PRIMARY KEY(id)
);