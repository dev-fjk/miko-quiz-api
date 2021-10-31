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