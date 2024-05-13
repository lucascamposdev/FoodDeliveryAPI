CREATE TABLE stores (
    id BIGSERIAL,
    name VARCHAR(255),

    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,

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