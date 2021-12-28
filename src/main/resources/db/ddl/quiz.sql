drop table if exists quiz;
create table quiz
(
    quiz_id    integer generated always as identity primary key,
    question   varchar(200)                           not null,
    commentary varchar(200)                           not null,
    answer_id  integer                                not null,
    status     quiz_status                            not null,
    created_at timestamp(3) default current_timestamp not null,
    update_at  timestamp(3) default current_timestamp not null
);

comment on column quiz.quiz_id is 'クイズID';
comment on column quiz.question is '問題文';
comment on column quiz.commentary is '解説文';
comment on column quiz.answer_id is '回答ID';
comment on column quiz.status is 'クイズステータス';
comment on column quiz.created_at is 'レコード作成日';
comment on column quiz.update_at is 'レコード更新日';