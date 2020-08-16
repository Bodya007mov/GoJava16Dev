select p.name
from projects p
where p.cost = (
    select min(p.cost)
    from projects p
);