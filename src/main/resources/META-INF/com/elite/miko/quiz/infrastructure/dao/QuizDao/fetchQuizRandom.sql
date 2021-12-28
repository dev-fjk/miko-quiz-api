select
    tbl.quiz_id,
    question,
    commentary,
    status
from
    quiz as tbl,
    (select
         quiz_id
     from
         quiz
     order by
         random()
    ) as random
where
    tbl.status = 'enabled' and
    tbl.quiz_id = random.quiz_id
limit
    /* count */10