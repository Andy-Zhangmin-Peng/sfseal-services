-- auto Generated on 2020-02-18
-- DROP TABLE IF EXISTS sfseal_user.account_role_xref;
CREATE TABLE sfseal_user.account_role_xref
(
    xref_id          bigserial PRIMARY KEY       NOT NULL,
    account_id       bigint                      NOT NULL,
    role_id          bigint                      NOT NULL,
    create_date      timestamp without time zone NOT NULL,
    last_update_date timestamp without time zone NOT NULL,
    last_update_user varchar(200)                NOT NULL,
    CONSTRAINT account_role_xref_account_id_fkey FOREIGN KEY (account_id)
        REFERENCES sfseal_user.account (account_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT account_role_xref_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES sfseal_user.role (role_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);


COMMENT ON COLUMN sfseal_user.account_role_xref.xref_id IS '关系系ID';
COMMENT ON COLUMN sfseal_user.account_role_xref.account_id IS '账户ID';
COMMENT ON COLUMN sfseal_user.account_role_xref.role_id IS '角色ID';
COMMENT ON COLUMN sfseal_user.account_role_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_user.account_role_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_user.account_role_xref.last_update_user IS '上次更新人';

CREATE TABLE sfseal_user.account_role_xref_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_user.account_role_xref
);

alter table sfseal_user.account_role_xref add constraint account_role unique(account_id,role_id);