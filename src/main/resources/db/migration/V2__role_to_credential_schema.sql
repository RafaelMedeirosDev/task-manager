create type ROLE as enum (
    'ROLE_USER',
    'ROLE_ADM'
);

ALTER TABLE credentials
ADD COLUMN role ROLE NOT NULL DEFAULT 'ROLE_USER';