CREATE TABLE public.course (
    course_id integer NOT NULL,
    course_code char(8) NOT NULL,
    course_name text NOT NULL,
    course_duration integer NOT NULL,
    course_price integer NOT NULL,
    PRIMARY KEY (course_id)
);
ALTER TABLE public.course
    ADD UNIQUE (course_code);

CREATE TABLE public.customer (
    customer_id integer NOT NULL,
    customer_name text NOT NULL,
    customer_phone char(10) NOT NULL,
    customer_email char(20) NOT NULL,
    PRIMARY KEY (customer_id)
);
ALTER TABLE public.customer
    ADD UNIQUE (customer_email);

CREATE TABLE public.app (
    app_id integer NOT NULL,
    app_datetime timestamp with time zone NOT NULL,
    product integer NOT NULL,
    customer integer NOT NULL,
    PRIMARY KEY (app_id)
);

CREATE INDEX ON public.app
    (product);
CREATE INDEX ON public.app
    (customer);

CREATE TABLE public.trainer (
    trainer_id integer NOT NULL,
    trainer_name text NOT NULL,
    trainer_surname text NOT NULL,
    trainer_email char(20) NOT NULL,
    PRIMARY KEY (trainer_id)
);
ALTER TABLE public.trainer
    ADD UNIQUE (trainer_email);

CREATE TABLE public.product (
    product_id integer NOT NULL,
    product_course integer NOT NULL,
    product_trainer integer NOT NULL,
    product_date timestamp with time zone NOT NULL,
    PRIMARY KEY (product_id)
);

CREATE INDEX ON public.product
    (product_course);
CREATE INDEX ON public.product
    (product_trainer);

INSERT INTO app VALUES (1, '2022-01-01 10:23:54', 1, 1);
INSERT INTO app VALUES (2, '2022-02-01 16:23:05', 1, 2);
INSERT INTO app VALUES (3, '2022-02-01 13:03:05', 2, 3);
INSERT INTO app VALUES (4, '2022-02-11 18:20:05', 3, 2);
INSERT INTO app VALUES (5, '2022-03-15 09:12:34', 1, 1);
INSERT INTO app VALUES (6, '2022-02-01 16:23:05', 4, 2);
INSERT INTO app VALUES (7, '2022-02-01 16:07:25', 5, 3);
INSERT INTO app VALUES (8, '2022-02-11 12:42:52', 5, 7);
INSERT INTO app VALUES (9, '2022-01-01 10:23:54', 1, 1);
INSERT INTO app VALUES (10, '2022-02-01 16:23:05', 1, 2);
INSERT INTO app VALUES (13, '2022-02-01 14:03:05', 2, 3);
INSERT INTO app VALUES (45, '2022-07-10 19:20:05', 7, 2);
INSERT INTO app VALUES (51, '2022-03-05 09:12:34', 1, 1);
INSERT INTO app VALUES (26, '2022-08-11 15:23:05', 6, 2);
INSERT INTO app VALUES (33, '2022-06-19 11:07:25', 5, 3);
INSERT INTO app VALUES (44, '2022-05-11 12:42:52', 11, 4);
INSERT INTO app VALUES (14, '2022-03-07 09:12:32', 6, 7);

INSERT INTO customer VALUES (1, 'Kate', 1234567890, 'kate@email.ru');
INSERT INTO customer VALUES (2, 'Peter', 9604602312, 'peter@email.ru');
INSERT INTO customer VALUES (3, 'Vasya', 4324896011, 'vasya@email.ru');
INSERT INTO customer VALUES (4, 'Elena', 9373601443, 'elena@email.ru');
INSERT INTO customer VALUES (5, 'Vladimir', 3244567012, 'vladimir@email.ru');
INSERT INTO customer VALUES (6, 'Nikita', 1234567890, 'nikita@email.ru');
INSERT INTO customer VALUES (7, 'Dima', 9597473156, 'dima@email.ru');
INSERT INTO customer VALUES (8, 'Boris', 4954289123, 'boris@email.ru');
INSERT INTO customer VALUES (9, 'Nina', 9797283152, 'nina@email.ru');
INSERT INTO customer VALUES (10, 'Alex', 9317473141, 'alex@email.ru');

INSERT INTO trainer VALUES (1, 'Anna', 'Vi', 'anna@email.ru');
INSERT INTO trainer VALUES (2, 'Nick', 'Ko', 'nick@email.ru');
INSERT INTO trainer VALUES (3, 'Olga', 'Bu', 'olga@email.ru');

INSERT INTO course VALUES (1, 'TTIS', 'Technical task and SRS', 12, 20);
INSERT INTO course VALUES (2, 'AIRF', 'AirFlow Cluster Andministrator', 24, 60);
INSERT INTO course VALUES (3, 'PSYM', 'Psyhology of Management', 8, 10);
INSERT INTO course VALUES (4, 'SAL', 'Psyhology of Sales', 16, 20);
INSERT INTO course VALUES (5, 'BAMP', 'Business Analysis Management and Planning', 8, 15);
INSERT INTO course VALUES (6, 'FTOP', 'Business Analysis for Managers', 8, 15);
INSERT INTO course VALUES (7, 'KAFKA', 'Kafka Cluster Administrator', 8, 15);

INSERT INTO product VALUES (1, 1, 1, '2022-05-17 10:23:54');
INSERT INTO product VALUES (2, 2, 2, '2022-08-21 10:23:54');
INSERT INTO product VALUES (3, 3, 3, '2022-09-12 10:23:54');
INSERT INTO product VALUES (4, 6, 1, '2022-04-07 10:23:54');
INSERT INTO product VALUES (5, 6, 3, '2022-06-20 10:23:54');
INSERT INTO product VALUES (6, 1, 1, '2022-05-17 10:23:54');
INSERT INTO product VALUES (7, 2, 2, '2022-11-01 10:23:54');
INSERT INTO product VALUES (8, 3, 3, '2022-05-04 10:23:54');
INSERT INTO product VALUES (9, 7, 2, '2022-07-07 10:23:54');
INSERT INTO product VALUES (10, 4, 3, '2022-06-20 10:23:54');
INSERT INTO product VALUES (11, 1, 1, '2022-09-27 10:23:54');