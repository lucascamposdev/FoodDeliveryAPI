ALTER TABLE stores
ALTER COLUMN latitude SET NOT NULL;

ALTER TABLE stores
ALTER COLUMN longitude SET NOT NULL;

ALTER TABLE stores
ADD COLUMN delivery_radius INTEGER;