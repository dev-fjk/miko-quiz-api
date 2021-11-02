select
    q.id,
    q.answer_id,
    q.question,
    q.commentary,
    q.status_id,
    a.answer1,
    a.answer2,
    a.answer3,
    a.answer4,
    a.correct_number
from
    quiz q
        join answer a on a.id = q.answer_id
where
    q.id IN /* idList */(1, 2)
order by
    random()
limit /* count */10

