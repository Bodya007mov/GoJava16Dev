create type sex_type as enum('male', 'female');
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
	foreign key (developer_id) references developers (id),
	foreign key (skill_id) references skills (id)
);

drop table if exists developers_projects cascade;
create table developers_projects
(
	developer_id integer,
	project_id integer,
	primary key (developer_id, project_id),
	foreign key (developer_id) references developers (id),
	foreign key (project_id) references projects (id)
);

alter table skills
add language_id integer references languages (id);

alter table projects
add company_id integer references companies (id);

alter table projects
add customer_id integer references customers (id);