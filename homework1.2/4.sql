alter table projects
add cost integer;

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

