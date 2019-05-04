CREATE TABLE vacancy(
    id serial primary key,
    author varchar(50),
    name varchar(1024),
    text text,
    link text,
    source varchar(200),
    date timestamp
);