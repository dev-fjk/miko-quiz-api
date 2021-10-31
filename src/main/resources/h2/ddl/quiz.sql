drop table if exists quiz;
create table quiz
(
    id         integer generated always as identity,
    question   varchar(200)                           not null,
    commentary varchar(200)                           not null,
    answer_id integer                                not null,
    status_id  integer                                not null,
    created_at timestamp(3) default current_timestamp not null,
    update_at  timestamp(3) default current_timestamp not null,
    primary key (id, answer_id),
    foreign key (answer_id) references answers (id),
    foreign key (status_id) references quiz_status (id)
);

comment on column quiz.id is 'クイズID';
comment on column quiz.question is '問題文';
comment on column quiz.commentary is '解説文';
comment on column quiz.answer_id is '回答ID';
comment on column quiz.status_id is 'クイズステータスID';
comment on column quiz.created_at is 'レコード作成日';
comment on column quiz.update_at is 'レコード更新日';