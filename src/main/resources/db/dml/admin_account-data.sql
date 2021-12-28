-- password + saltでハッシュ化した文字列を登録
insert into
    admin_account (account_id, password)
values
    ('root', 'f6ca968fe4b340bdbaef14d0803986ad2ae611fc651c5e00094db5a9f08251f6');