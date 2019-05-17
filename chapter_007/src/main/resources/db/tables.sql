create table users
(
    id       serial not null primary key,
    name     varchar(30),
    login    varchar(40) not null unique,
    email    varchar(50) not null unique,
    password varchar(32)
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

insert into roles(name, default_role) values ('Admin', false), ('User', true), ('Moder', false);
insert into users(name, login, email, password)
VALUES
       ('first user', 'root', 'root@root', 'root'),
       ('second user', 'user', 'user@user', 'user'),
       ('third user', 'moder', 'mod@mod', 'mod');
insert into role_user(role_id, user_id) VALUES (1, 1), (2, 2), (3, 3);