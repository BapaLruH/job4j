INSERT INTO role(NAME)
VALUES ('ADMIN'),
       ('USER');
INSERT INTO rules(NAME)
VALUES ('READ'),
       ('WRITE');
INSERT INTO role_rules(id_role, id_rules)
VALUES (1, 1),
       (1, 2),
       (2, 1);

INSERT INTO users(NAME, ID_ROLE)
VALUES ('PAVEL', 1),
       ('YULIYA', 2),
       ('ALEXANDR', 1),
       ('DMITRIY', 2),
       ('SERGEY', 2),
       ('ANTON', 2);

INSERT INTO category(NAME)
VALUES ('LOW'),
       ('MIDLE'),
       ('HIGH');
INSERT INTO state(NAME)
VALUES ('QUEUE'),
       ('IN_PROGRES'),
       ('CLOSE'),
       ('REOPEN_QUEUE');

INSERT INTO item(TITLE, ID_AUTHOR, DESCRIPTION, ID_STATE, ID_CATEGORY)
VALUES ('Доступ к БД', 2, 'Прошу предоставить доступ к базе', 1, 3);

INSERT INTO item(TITLE, ID_AUTHOR, DESCRIPTION, ID_STATE, ID_CATEGORY)
VALUES ('Доступ к папке на сетевом диске', 2, 'Прошу предоставить доступ к папке на диске Х', 1, 2);

INSERT INTO item(TITLE, ID_AUTHOR, ID_STATE, ID_CATEGORY)
VALUES ('Срочно нужен доступ к серверу', 6, 1, 3);

INSERT INTO comments(id_USER, ID_ITEM, description)
VALUES (6, 3, 'Коллеги, сориентируйте по срокам пожалуста.'),
       (1, 1, 'Прошу указать обоснование'),
       (1, 1, 'К каким таблицам нужен доступ?'),
       (3, 2, 'Укажите точный путь к папке');