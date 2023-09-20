CREATE TABLE accounts
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    balance  INTEGER     NOT NULL
);
