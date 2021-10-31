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