create table country
(
    id serial not null primary key,
    name varchar(50)
);

create table city
(
    id serial not null primary key,
    name varchar(50),
    country_id integer references country on update cascade
);

create table users
(
    id       serial not null primary key,
    name     varchar(30),
    login    varchar(40) not null unique,
    email    varchar(50) not null unique,
    password varchar(32),
    city_id integer references city on update cascade
);

create table roles
(
    id   serial not null primary key,
    name varchar(50) not null unique,
    default_role boolean
);

create table role_user
(
    id      serial not null constraint role_user_pkey primary key,
    role_id integer references roles on update cascade on delete cascade,
    user_id integer references users on update cascade on delete cascade
);

insert into country(name) values ('Russia'), ('America'), ('Other');
insert into city(name, country_id) values ('Moscow', 1), ('Rostov-on-Don', 1), ('New York', 2), ('Los Angeles', 2), ('Philadelphia', 2), ('For delete', 3);
insert into roles(name, default_role) values ('Admin', false), ('User', true), ('Moder', false);
insert into users(name, login, email, password, city_id)
VALUES
       ('first user', 'root', 'root@root', 'root', 1),
       ('second user', 'user', 'user@user', 'user', 3),
       ('third user', 'moder', 'mod@mod', 'mod', 5);
insert into role_user(role_id, user_id) VALUES (1, 1), (2, 2), (3, 3);