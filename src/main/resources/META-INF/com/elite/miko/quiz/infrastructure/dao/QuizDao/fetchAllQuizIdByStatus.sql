select
    q.id
from
    quiz q
        join quiz_status qs on qs.id = q.status_id
where
    qs.status = /* statusName */'apply'

