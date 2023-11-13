alter table sfseal_company.lock
    add lock_position varchar(200);
alter table sfseal_company.lock_history
    add lock_position varchar(200);

alter table sfseal_company.lock
    add lock_user_id bigint;

alter table sfseal_company.lock
    add unlock_user_id bigint;

alter table sfseal_company.lock_history
    add lock_user_id bigint;

alter table sfseal_company.lock_history
    add unlock_user_id int;

alter table sfseal_company.work_order
    add temp_carriera varchar(200);

comment on column sfseal_company.work_order.temp_carriera is '临时承运人';

alter table sfseal_company.work_order_history
    add temp_carriera varchar(200);

alter table sfseal_company.work_order
    add temp_transport varchar(200);

comment on column sfseal_company.work_order.temp_transport is '临时运输载体';

alter table sfseal_company.work_order_lock_xref_history
    add temp_transport varchar(200);

alter table sfseal_company.lock
    add tran_id bigint;

comment on column sfseal_company.lock.tran_id is '实际运输载体号';

alter table sfseal_company.lock_history
    add tran_id bigint;

alter table sfseal_company.work_order
    add waybill_number varchar(200);

comment on column sfseal_company.work_order.waybill_number is '运单号';

alter table sfseal_company.work_order_history
    add waybill_number varchar(200);



