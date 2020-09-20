drop type if exists sex_type cascade;
create type sex_type as enum('male', 'female');
drop type if exists level_type cascade;
create type level_type as enum('junior', 'middle', 'senior');

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
    deadline date
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
('Vasyl', 20, 'male'),
('Masha', 21, 'female'),
('Petro', 22, 'male'),
('Olga', 23, 'female'),
('Dmytro', 24, 'male'),
('Olena', 25, 'female');

insert into languages (name)
values
('Java'),
('C++'),
('C#'),
('JS');

insert into skills (level, language_id)
values
('junior', 1),
('middle', 1),
('senior', 1),
('junior', 2),
('middle', 2),
('senior', 2),
('junior', 3),
('middle', 3),
('senior', 3),
('junior', 4),
('middle', 4),
('senior', 4);

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

insert into projects (name, deadline, company_id, customer_id)
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
    add salary integer;

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