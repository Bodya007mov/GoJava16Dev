select p.name
from developers d
join developers_projects dp on d.id = dp.developer_id
join projects p on dp.project_id = p.id
group by p.name
having sum(d.salary) =  (
                            select sum(d.salary) as s
                            from developers d
                            join developers_projects dp on d.id = dp.developer_id
                            group by dp.project_id
                            order by s desc
                            limit 1
                        )
;