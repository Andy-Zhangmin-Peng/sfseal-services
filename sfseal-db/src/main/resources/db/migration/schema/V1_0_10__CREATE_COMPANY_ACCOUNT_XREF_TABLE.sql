-- auto Generated on 2020-02-24
-- DROP TABLE IF EXISTS sfseal_company.company_account_xref;
CREATE TABLE sfseal_company.company_account_xref
(
    xref_id          bigserial PRIMARY KEY       NOT NULL,
    company_id       bigint                      NOT NULL,
    account_id       bigint                      NOT NULL,
    create_date      timestamp without time zone NOT NULL,
    last_update_date timestamp without time zone NOT NULL,
    last_update_user varchar(200)                NOT NULL,
    CONSTRAINT company_account_xref_company_id_fkey foreign key (company_id)
        references sfseal_company.company (company_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT company_account_xref_account_id_fkey foreign key (account_id)
        references sfseal_user.account (account_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE UNIQUE INDEX company_account_xref_company_id_account_id ON sfseal_company.company_account_xref (company_id, account_id);
COMMENT ON COLUMN sfseal_company.company_account_xref.xref_id IS '关系ID';
COMMENT ON COLUMN sfseal_company.company_account_xref.company_id IS '公司ID';
COMMENT ON COLUMN sfseal_company.company_account_xref.account_id IS '账户ID';
COMMENT ON COLUMN sfseal_company.company_account_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.company_account_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.company_account_xref.last_update_user IS '上次更新人';

CREATE TABLE sfseal_company.company_account_xref_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.company_account_xref
);
