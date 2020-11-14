drop type if exists sex_type cascade;
create type sex_type as enum('MALE', 'FEMALE');
drop type if exists level_type cascade;
create type level_type as enum('JUNIOR', 'MIDDLE', 'SENIOR');

drop table if exists developers cascade;
create table developers
(
    id serial primary key,
    name varchar(50),
    age integer,
    sex sex_type
);

drop table if exists skills cascade;
create table skills
(
    id serial primary key,
    level level_type
);

drop table if exists languages cascade;
create table languages
(
    id serial primary key,
    name varchar(50)
);

drop table if exists projects cascade;
create table projects
(
    id serial primary key,
    name varchar(50),
    creation_date date
);

drop table if exists companies cascade;
create table companies
(
    id serial primary key,
    name varchar(50),
    phone varchar(12)
);

drop table if exists customers cascade;
create table customers
(
    id serial primary key,
    name varchar(50),
    phone varchar(12)
);

drop table if exists developers_skills cascade;
create table developers_skills
(
    developer_id integer,
    skill_id integer,
    primary key (developer_id, skill_id),
    foreign key (developer_id) references developers (id) on delete cascade,
    foreign key (skill_id) references skills (id) on delete cascade
);

drop table if exists developers_projects cascade;
create table developers_projects
(
    developer_id integer,
    project_id integer,
    primary key (developer_id, project_id),
    foreign key (developer_id) references developers (id) on delete cascade,
    foreign key (project_id) references projects (id) on delete cascade
);

alter table skills
    add language_id integer references languages (id) on delete cascade;

alter table projects
    add company_id integer references companies (id) on delete cascade;

alter table projects
    add customer_id integer references customers (id) on delete cascade;

insert into developers (name, age, sex)
values
('Vasyl', 20, 'MALE'),
('Masha', 21, 'FEMALE'),
('Petro', 22, 'MALE'),
('Olga', 23, 'FEMALE'),
('Dmytro', 24, 'MALE'),
('Olena', 25, 'FEMALE');

insert into languages (name)
values
('Java'),
('C++'),
('C#'),
('JS');

insert into skills (level, language_id)
values
('JUNIOR', 1),
('MIDDLE', 1),
('SENIOR', 1),
('JUNIOR', 2),
('MIDDLE', 2),
('SENIOR', 2),
('JUNIOR', 3),
('MIDDLE', 3),
('SENIOR', 3),
('JUNIOR', 4),
('MIDDLE', 4),
('SENIOR', 4);

insert into developers_skills
values
(1, 3),
(1, 7),
(2, 7),
(2, 2),
(3, 11),
(4, 6),
(5, 4),
(6, 12);

insert into customers (name, phone)
values
('Idrosanitaria Bonomi', '390612345678'),
('Caleffi', '390612345679'),
('General Fittings', '390612345611'),
('Epicentr', '380631236548');

insert into companies (name, phone)
values
('EPAM', '380688524569'),
('Global Logic', '380632365875'),
('LuxSoft', '380505321569');

insert into projects (name, creation_date, company_id, customer_id)
values
('online store', '10/09/2020', 1, 3),
('mobile application', '30/08/2020', 2, 4),
('crm', '25/10/2020', 3, 3),
('database development', '24/08/2020', 2, 1);

insert into developers_projects
values
(1, 1),
(1, 2),
(1, 4),
(2, 2),
(2, 3),
(3, 3),
(4, 2);

alter table developers
    add salary double precision;

update developers
set salary = 50000
where id = 1;
update developers
set salary = 45000
where id = 2;
update developers
set salary = 40000
where id = 3;
update developers
set salary = 35000
where id = 4;
update developers
set salary = 30000
where id = 5;
update developers
set salary = 25000
where id = 6;

alter table projects
    add cost double precision;

update projects
set cost = 35000
where id = 1;
update projects
set cost = 100000
where id = 2;
update projects
set cost = 50000
where id = 3;
update projects
set cost = 45000
where id = 4;