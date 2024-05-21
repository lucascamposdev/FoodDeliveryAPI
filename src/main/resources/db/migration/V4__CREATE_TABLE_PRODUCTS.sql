CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(10, 2),

    store_id BIGINT,
    FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE
);

