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

-- answer
drop table if exists answer;
create table answer
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

comment on column answer.id is '回答ID';
comment on column answer.answer1 is '回答1';
comment on column answer.answer2 is '回答2';
comment on column answer.answer3 is '回答3';
comment on column answer.answer4 is '回答4';
comment on column answer.correct_number is '正解の回答番号';
comment on column answer.created_at is 'レコード作成日';
comment on column answer.update_at is 'レコード更新日';

-- quiz
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
    foreign key (answer_id) references answer (id),
    foreign key (status_id) references quiz_status (id)
);

comment on column quiz.id is 'クイズID';
comment on column quiz.question is '問題文';
comment on column quiz.commentary is '解説文';
comment on column quiz.answer_id is '回答ID';
comment on column quiz.status_id is 'クイズステータスID';
comment on column quiz.created_at is 'レコード作成日';
comment on column quiz.update_at is 'レコード更新日';


-- status
insert into
    quiz_status (status)
values
    ('apply');
insert into
    quiz_status (status)
values
    ('request');
insert into
    quiz_status (status)
values
    ('exclusion');

-- answer
insert into
    answer (answer1, answer2, answer3, answer4, correct_number)
values
    ('にゃっはろー', 'こんこんきーつね', 'ゆびゆび～', 'こんるし～', 1);

insert into
    answer (answer1, answer2, answer3, answer4, correct_number)
values
    ('みこファン', '杞憂おじさん', '35p', '星詠み', 3);

insert into
    answer (answer1, answer2, answer3, answer4, correct_number)
values
    ('0期生', '1期生', '2期生', '3期生', 1);

insert into
    answer (answer1, answer2, answer3, answer4, correct_number)
values
    ('request1', 'request2', 'request3', 'request4', 2);

insert into
    answer (answer1, answer2, answer3, answer4, correct_number)
values
    ('dummy1', 'dummy2', 'dummy3', 'dummy4', 4);

-- quiz
insert into
    quiz (question, commentary, answer_id, status_id)
values
    ('みこちの挨拶といえば？', 'みこちのあいさつはにゃっはろー', 1, 1);
insert into
    quiz (question, commentary, answer_id, status_id)
values
    ('みこちのファンの名称は？', 'みこちのファンの名称は35p', 2, 1);
insert into
    quiz (question, commentary, answer_id, status_id)
values
    ('みこちはホロライブ何期生？', 'みこちはホロライブ0期生です', 3, 1);
insert into
    quiz (question, commentary, answer_id, status_id)
values
    ('リクエスト問題', 'リクエスト問題です', 4, 2);
insert into
    quiz (question, commentary, answer_id, status_id)
values
    ('除外問題', '除外問題です', 5, 3);