drop table if exists quiz;
create table quiz
(
    quiz_id        integer generated always as identity primary key,
    question       varchar(200)                           not null,
    answer1        varchar(50)                            not null,
    answer2        varchar(50)                            not null,
    answer3        varchar(50)                            not null,
    answer4        varchar(50)                            not null,
    correct_number smallint                               not null,
    status         varchar(20)                            not null,
    created_at     timestamp(0) default current_timestamp not null,
    create_user    varchar(50),
    update_at      timestamp(0) default current_timestamp not null,
    update_user    varchar(50),
    version        integer      default 0                 not null
);

comment on column quiz.quiz_id is 'クイズID';
comment on column quiz.question is '問題文';
comment on column quiz.answer1 is '回答1';
comment on column quiz.answer2 is '回答2';
comment on column quiz.answer3 is '回答3';
comment on column quiz.answer4 is '回答4';
comment on column quiz.correct_number is '正解の回答番号';
comment on column quiz.status is 'クイズステータス';
comment on column quiz.created_at is 'レコード作成日';
comment on column quiz.create_user is 'レコード作成者';
comment on column quiz.update_at is 'レコード更新日';
comment on column quiz.update_user is '最終更新者';
comment on column quiz.version is 'バージョン';