select avg(d.salary)
from developers d
join developers_projects dp on d.id = dp.developer_id
join projects p on dp.project_id = p.id
group by p.name, p.cost
having p.cost = (
    select min(p.cost)
    from projects p
);