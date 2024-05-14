CREATE TABLE stores (
    id BIGSERIAL,
    name VARCHAR(255),

    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,

    delivery_radius INTEGER,

    address_street VARCHAR(100),
    address_number VARCHAR(10),
    address_complement VARCHAR(50),
    address_neighborhood VARCHAR(50),
    address_city VARCHAR(50),
    address_state VARCHAR(20),
    address_cep VARCHAR(20),

    PRIMARY KEY(id)
);

CREATE TABLE opening_hours (
    id BIGSERIAL,
    day_of_week VARCHAR(10),
    opening TIME,
    closing TIME,
    store_id BIGINT,
    FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE,

    PRIMARY KEY(id)
);