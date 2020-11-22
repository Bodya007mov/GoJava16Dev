drop type if exists role_type cascade;
create type role_type as enum('ADMIN', 'USER');

drop table if exists products cascade;
create table products
(
    id serial primary key,
    name varchar(50),
    price double precision
);

drop table if exists manufacturers cascade;
create table manufacturers
(
    id serial primary key,
    name varchar(50)
);

drop table if exists roles cascade;
create table roles
(
    id serial primary key,
    role role_type
);

drop table if exists users cascade;
create table users
(
    id serial primary key,
    email varchar(50),
    password varchar(50),
    first_name varchar(50),
    last_name varchar(50)
);

alter table products
    add manufacturer_id integer references manufacturers (id) on delete cascade;

alter table users
    add role_id integer references roles (id) on delete cascade;

insert into manufacturers (name)
values
('Idrosanitaria Bonomi'), ('Caleffi'), ('General Fittings');

insert into products (name, price, manufacturer_id)
values
('Ball faucet', 4.67, 1),
('Main filter', 3.42, 1),
('Radiator faucet', 8.25, 2),
('Diaphragm valve', 5.36, 2),
('Tension sleeve', 2.45, 3),
('adapter', 3.68, 3);

insert into roles (role)
values
('ADMIN'), ('USER');

insert into users (email, password, first_name, last_name, role_id)
values
('first_user@mail.com', 'password1', 'Vasyl', 'Melnyk', 1),
('second_user@mail.com', 'password2', 'Mykola', 'Chub', 2),
('third_user@mail.com', 'password3', 'Olena', 'Gai', 2);