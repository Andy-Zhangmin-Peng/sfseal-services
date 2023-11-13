/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

-- auto Generated on 2020-02-18
-- DROP TABLE IF EXISTS account;
CREATE SCHEMA IF NOT EXISTS sfseal_user;
CREATE SCHEMA IF NOT EXISTS sfseal_company;

CREATE TABLE sfseal_user.account
(
    account_id       BIGSERIAL PRIMARY KEY          NOT NULL,
    username         varchar(200)                   NOT NULL,
    password         varchar(200)                   NOT NULL,
    phone_number     varchar(200)                   NOT NULL,
    email            varchar(200),
    create_date      TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
    last_update_date TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
    last_update_user varchar(200) DEFAULT ''        NOT NULL
);

CREATE UNIQUE INDEX ux_account_account_id ON sfseal_user.account (account_id);
CREATE UNIQUE INDEX ux_account_username ON sfseal_user.account (username);
CREATE UNIQUE INDEX ux_account_phone_number ON sfseal_user.account (phone_number);
COMMENT ON COLUMN sfseal_user.account.account_id IS '管理员账号ID';
COMMENT ON COLUMN sfseal_user.account.username IS '登录名';
COMMENT ON COLUMN sfseal_user.account.password IS '密码';
COMMENT ON COLUMN sfseal_user.account.phone_number IS '管理员手机号';
COMMENT ON COLUMN sfseal_user.account.email IS '管理员邮箱';
COMMENT ON COLUMN sfseal_user.account.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_user.account.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_user.account.last_update_user IS '上次更新人';

CREATE TABLE sfseal_user.account_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_user.account
);

