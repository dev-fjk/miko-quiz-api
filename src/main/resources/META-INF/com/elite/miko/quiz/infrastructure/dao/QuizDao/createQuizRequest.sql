insert into
    quiz (question,
          commentary,
          answer_id,
          status_id)
values
    (
        /* entity.question  */'問題',
        /* entity.commentary  */'解説文',
        /* entity.answerId  */1,
        (select id from quiz_status where status = 'request')
    );