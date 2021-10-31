-- status
drop table if exists quiz_status;
create table quiz_status
(
    id         integer generated always as identity primary key,
    status     varchar(20)                            not null,
    created_at timestamp(3) default current_timestamp not null,
    update_at  timestamp(3) default current_timestamp not null
);

comment on column quiz_status.id is 'ステータスID';
comment on column quiz_status.status is 'クイズステータス';
comment on column quiz_status.created_at is 'レコード作成日';
comment on column quiz_status.update_at is 'レコード更新日';

-- answers
drop table if exists answers;
create table answers
(
    id             integer generated always as identity primary key,
    answer1        varchar(50)                            not null,
    answer2        varchar(50)                            not null,
    answer3        varchar(50)                            not null,
    answer4        varchar(50)                            not null,
    correct_number smallint                               not null,
    created_at     timestamp(3) default current_timestamp not null,
    update_at      timestamp(3) default current_timestamp not null
);

comment on column answers.id is '回答ID';
comment on column answers.answer1 is '回答1';
comment on column answers.answer2 is '回答2';
comment on column answers.answer3 is '回答3';
comment on column answers.answer4 is '回答4';
comment on column answers.correct_number is '正解の回答番号';
comment on column answers.created_at is 'レコード作成日';
comment on column answers.update_at is 'レコード更新日';

-- quiz
drop table if exists quiz;
create table quiz
(
    id         integer generated always as identity primary key,
    answers_id integer                                not null,
    status_id  integer                                not null,
    question   varchar(200)                           not null,
    commentary varchar(200)                           not null,
    created_at timestamp(3) default current_timestamp not null,
    update_at  timestamp(3) default current_timestamp not null,
    foreign key (answers_id) references answers (id),
    foreign key (status_id) references quiz_status (id)
);

comment on column quiz.id is 'クイズID';
comment on column quiz.status_id is '回答ID';
comment on column quiz.status_id is 'クイズステータスID';
comment on column quiz.question is '問題文';
comment on column quiz.commentary is '解説文';
comment on column quiz.created_at is 'レコード作成日';
comment on column quiz.update_at is 'レコード更新日';