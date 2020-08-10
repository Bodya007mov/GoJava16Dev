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