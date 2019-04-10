create database items;

create table role
(
  id   serial primary key,
  name varchar(20)
);

create table rules
(
  id   serial primary key,
  name varchar(50)
);

create table role_rules
(
  id       serial primary key,
  role_id  int references role (id),
  rules_id int references rules (id)
);

create table usr
(
  id      serial primary key,
  name    varchar(20),
  role_id int references role (id)
);

create table category
(
  id   serial primary key,
  name varchar(50)
);

create table state
(
  id   serial primary key,
  name varchar(20)
);

create table item
(
  id          serial primary key,
  name        varchar(50),
  text        text,
  date        date,
  usr_id      int references usr (id),
  category_id int references category (id),
  state_id    int references state (id)
);

create table comments
(
  id      serial primary key,
  name    varchar(30),
  text    text,
  item_id int references item (id)
);

create table attachs
(
  id      serial primary key,
  name    varchar(30),
  attachs bytea,
  item_id int references item (id)
);

insert into role(name)
values ('manager'),
       ('employee');
insert into rules(name)
values ('drink coffee'),
       ('set a schedule');
insert into role_rules(role_id, rules_id)
VALUES (1, 1),
       (1, 2),
       (2, 1);
insert into usr(name, role_id)
VALUES ('Ivan', 1),
       ('Fedor', 2);
insert into category(name)
values ('info'),
       ('error');
insert into state(name)
values ('in process'),
       ('completed');
insert into item(name, text, date, usr_id, category_id, state_id)
VALUES ('first item', 'some text', 'Jan-08-1999', 1, 1, 1),
       ('second item', 'any text', 'Jan-16-1999', 2, 1, 2);
insert into comments(name, text, item_id)
VALUES ('first comment', 'text_1', 1),
       ('first next comment', 'text_2', 1),
       ('second comment', 'text_3', 2);
insert into attachs(name, attachs, item_id)
VALUES ('first file', bytea'101', 1),
       ('first file', bytea'202', 2);