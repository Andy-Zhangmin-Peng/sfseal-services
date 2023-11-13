-- auto Generated on 2020-02-18
-- DROP TABLE IF EXISTS sfseal_user.user;
CREATE TABLE sfseal_user.user
(
    user_id          bigserial PRIMARY KEY       NOT NULL,
    name             varchar(200),
    account_id       bigint,
    address_id       bigint,
    is_active        boolean                     NOT NULL,
    create_date      timestamp without time zone NOT NULL,
    last_update_date timestamp without time zone NOT NULL,
    last_update_user varchar(200)                NOT NULL
);
CREATE INDEX ix_user_name ON sfseal_user.user (name);

COMMENT ON COLUMN sfseal_user.user.user_id IS '用户ID';
COMMENT ON COLUMN sfseal_user.user.name IS '用户姓名';
COMMENT ON COLUMN sfseal_user.user.account_id IS '用户账号ID';
COMMENT ON COLUMN sfseal_user.user.address_id IS '用户地址ID';
COMMENT ON COLUMN sfseal_user.user.is_active IS '用户状态';
COMMENT ON COLUMN sfseal_user.user.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_user.user.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_user.user.last_update_user IS '上次更新人';

CREATE TABLE sfseal_user.user_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_user.user
);