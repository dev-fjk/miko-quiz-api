select
    quiz_id,
    question,
    commentary,
    status
from
    quiz
where
/*%if quizStatus != null */
    status = /* quizStatus */'request'
/*%end*/