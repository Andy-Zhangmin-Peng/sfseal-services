-- auto Generated on 2020-02-24
-- DROP TABLE IF EXISTS sfseal_company;
CREATE TABLE sfseal_company.company
(
    company_id       bigserial PRIMARY KEY       NOT NULL,
    company_name     varchar(200)                NOT NULL,
    phone_number     varchar(100)                NOT NULL,
    address          varchar(200)                NOT NULL,
    email            varchar(100)                NOT NULL,
    create_date      timestamp without time zone NOT NULL,
    last_update_date timestamp without time zone NOT NULL,
    last_update_user varchar(200)                NOT NULL
);
CREATE INDEX ix_sfseal_company_company_name ON sfseal_company.company (company_name);

COMMENT ON COLUMN sfseal_company.company.company_id IS '企业ID';
COMMENT ON COLUMN sfseal_company.company.company_name IS '企业名称';
COMMENT ON COLUMN sfseal_company.company.phone_number IS '企业电话';
COMMENT ON COLUMN sfseal_company.company.address IS '企业地址';
COMMENT ON COLUMN sfseal_company.company.email IS '企业邮箱';
COMMENT ON COLUMN sfseal_company.company.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.company.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.company.last_update_user IS '上次更新人';

CREATE TABLE sfseal_company.company_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.company
);
