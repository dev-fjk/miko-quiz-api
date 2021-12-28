select
    quiz_id,
    question,
    commentary,
    status
from
    quiz
where
    status = 'enabled'
order by
    random()