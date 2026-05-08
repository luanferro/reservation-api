ALTER TABLE reservations
ADD COLUMN end_date TIMESTAMP NOT NULL;

ALTER TABLE reservations
RENAME COLUMN date TO start_date;