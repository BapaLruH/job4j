create database movie_theater;

create table movies (
    id   serial primary key not null,
    name varchar(255),
    link varchar(255)
);

create table users (
    id serial primary key not null,
    name varchar(255),
    phone varchar(12)
);
create unique index users_name_phone_uindex on users (name, phone);

create table places(
    id serial primary key not null,
    name varchar(5) not null,
    price float4,
    row int,
    movie_id int references movies on update cascade
);
create unique index places_movie_id_name_row_uindex on places(name, row, movie_id);

create table usr_places (
    id serial primary key not null,
    usr_id int references users on update cascade on delete cascade,
    place_id int unique references places on update cascade on delete cascade
);

insert into movies(id, name, link) VALUES (1, 'Avengers endgame', '/moviesImgs/1-c.jpg'), (2, 'Avengers infinity war', '/moviesImgs/2-c.jpg');
insert into users(id, name, phone) VALUES (1, 'Fedor', '89000'), (2, 'Maxim', '789098');

insert into places(id, name, price, row, movie_id)
VALUES
/*1 movie_id*/
       (1, 1, 100, 1, 1), (2, 2, 100, 1, 1), (3, 3, 100, 1, 1), (4, 4, 100, 1, 1), (5, 5, 100, 1, 1),
       (6, 6, 100, 1, 1), (7, 7, 100, 1, 1), (8, 8, 100, 1, 1), (9, 9, 100, 1, 1), (10, 10, 100, 1, 1),

       (11, 1, 250, 2, 1), (12, 2, 250, 2, 1), (13, 3, 250, 2, 1), (14, 4, 250, 2, 1), (15, 5, 250, 2, 1),
       (16, 6, 250, 2, 1), (17, 7, 250, 2, 1), (18, 8, 250, 2, 1), (19, 9, 250, 2, 1), (20, 10, 250, 2, 1),

       (21, 1, 300, 3, 1), (22, 2, 300, 3, 1), (23, 3, 300, 3, 1), (24, 4, 300, 3, 1), (25, 5, 300, 3, 1),
       (26, 6, 300, 3, 1), (27, 7, 300, 3, 1), (28, 8, 300, 3, 1),

       (29, 1, 300, 4, 1), (30, 2, 300, 4, 1), (31, 3, 300, 4, 1), (32, 4, 300, 4, 1), (33, 5, 300, 4, 1),
       (34, 6, 300, 4, 1), (35, 7, 300, 4, 1), (36, 8, 300, 4, 1), (37, 9, 300, 4, 1), (38, 10, 300, 4, 1),

       (39, 1, 400, 5, 1), (40, 2, 400, 5, 1), (41, 3, 400, 5, 1), (42, 4, 400, 5, 1), (43, 5, 400, 5, 1),
       (44, 6, 400, 5, 1), (45, 7, 400, 5, 1), (46, 8, 400, 5, 1), (47, 9, 400, 5, 1), (48, 10, 400, 5, 1),
/*2 movie_id*/
       (49, 1, 100, 1, 2), (50, 2, 100, 1, 2), (51, 3, 100, 1, 2), (52, 4, 100, 1, 2), (53, 5, 100, 1, 2),
       (54, 6, 100, 1, 2), (55, 7, 100, 1, 2), (56, 8, 100, 1, 2),

       (57, 1, 100, 2, 2), (58, 2, 100, 2, 2), (59, 3, 100, 2, 2), (60, 4, 100, 2, 2), (61, 5, 100, 2, 2),
       (62, 6, 100, 2, 2), (63, 7, 100, 2, 2), (64, 8, 100, 2, 2),

       (65, 1, 100, 3, 2), (66, 2, 100, 3, 2), (67, 3, 100, 3, 2), (68, 4, 100, 3, 2), (69, 5, 100, 3, 2),
       (70, 6, 100, 3, 2), (71, 7, 100, 3, 2), (72, 8, 100, 3, 2);

insert into usr_places(usr_id, place_id)
VALUES (1, 22), (1, 23), (1, 24), (1, 25), (1, 26), (1, 27), (2, 41), (2, 42), (2, 43), (2, 44), (2, 45), (2, 46);

