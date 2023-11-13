--- auto Generated on 2020-02-18
-- DROP TABLE IF EXISTS staff;
CREATE TABLE sfseal_user.staff
(
    account_id          bigserial PRIMARY KEY       NOT NULL,
    name                varchar(200)                NOT NULL,
    mobile              varchar(200),
    office_phone_number varchar(200),
    address             varchar(200),
    is_active           boolean                     NOT NULL,
    create_date         timestamp without time zone NOT NULL,
    last_update_date    timestamp without time zone NOT NULL,
    last_update_user    varchar(200)                NOT NULL
);
CREATE INDEX ix_staff_name ON sfseal_user.staff (name);

COMMENT ON COLUMN sfseal_user.staff.account_id IS '所属账户ID';
COMMENT ON COLUMN sfseal_user.staff.name IS '管理员姓名';
COMMENT ON COLUMN sfseal_user.staff.mobile IS '管理员手机号';
COMMENT ON COLUMN sfseal_user.staff.office_phone_number IS '管理员办公电话';
COMMENT ON COLUMN sfseal_user.staff.address IS '管理员地址';
COMMENT ON COLUMN sfseal_user.staff.is_active IS '账户状态';
COMMENT ON COLUMN sfseal_user.staff.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_user.staff.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_user.staff.last_update_user IS '上次更新人';

CREATE TABLE sfseal_user.staff_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_user.staff
);