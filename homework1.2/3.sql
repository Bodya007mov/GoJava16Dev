select sum(d.salary)
from developers d
join developers_skills ds on d.id = ds.developer_id
join skills s on ds.skill_id = s.id
join languages l on s.language_id = l.id
group by l.name
having l.name = 'Java';
