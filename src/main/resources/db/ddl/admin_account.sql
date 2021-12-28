drop table if exists admin_account;
create table admin_account
(
	account_id varchar(12) primary key,
	password varchar(64) not null
);
comment on column admin_account.account_id is '管理者用のログインID';
comment on column admin_account.password is 'ハッシュ化済みパスワード';
