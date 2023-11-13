drop table if exists sfseal_user.user_address cascade;

drop table if exists sfseal_user.user_address_history cascade;

alter table sfseal_user."user" drop column address_id;

alter table sfseal_user."user"
    add mobile varchar(50);

comment on column sfseal_user."user".mobile is '用户手机号';

alter table sfseal_user."user"
    add address varchar(200);

comment on column sfseal_user."user".address is '用户地址';

alter table sfseal_user.user_history drop column address_id;

alter table sfseal_user.user_history
    add address varchar(200);

alter table sfseal_user.user_history
    add mobile varchar(50);

alter table sfseal_user.account alter column phone_number drop not null;

alter table sfseal_user.account_history alter column phone_number drop not null;

alter table sfseal_company.lock alter column bar_code type varchar(100) using bar_code::varchar(100);

alter table sfseal_company.lock_history alter column bar_code type varchar(100) using bar_code::varchar(100);





