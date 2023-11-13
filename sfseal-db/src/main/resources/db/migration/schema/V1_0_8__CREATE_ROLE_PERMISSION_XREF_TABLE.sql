-- auto Generated on 2020-02-18
-- DROP TABLE IF EXISTS sfseal_user.role_permission_xref;
CREATE TABLE sfseal_user.role_permission_xref
(
    xref_id          bigserial PRIMARY KEY       NOT NULL,
    role_id          bigint                      NOT NULL,
    permission_id    bigint                      NOT NULL,
    create_date      timestamp without time zone NOT NULL,
    last_update_date timestamp without time zone NOT NULL,
    last_update_user varchar(200)                NOT NULL,
    CONSTRAINT account_role_xref_permission_id_fkey FOREIGN KEY (permission_id)
        REFERENCES sfseal_user.permission (permission_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT account_role_xref_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES sfseal_user.role (role_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);


COMMENT ON COLUMN sfseal_user.role_permission_xref.xref_id IS '关系ID';
COMMENT ON COLUMN sfseal_user.role_permission_xref.role_id IS '角色ID';
COMMENT ON COLUMN sfseal_user.role_permission_xref.permission_id IS '权限ID';
COMMENT ON COLUMN sfseal_user.role_permission_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_user.role_permission_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_user.role_permission_xref.last_update_user IS '上次更新人';

CREATE TABLE sfseal_user.role_permission_xref_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_user.role_permission_xref
);

insert into sfseal_user.role_permission_xref (role_id, permission_id, create_date, last_update_date, last_update_user)
VALUES (1, 1, current_timestamp, current_timestamp, 'System_Admin'),
       (1, 2, current_timestamp, current_timestamp, 'System_Admin'),
       (1, 3, current_timestamp, current_timestamp, 'System_Admin'),
       (2, 4, current_timestamp, current_timestamp, 'System_Admin'),
       (2, 5, current_timestamp, current_timestamp, 'System_Admin'),
       (2, 6, current_timestamp, current_timestamp, 'System_Admin'),
       (2, 7, current_timestamp, current_timestamp, 'System_Admin'),
       (2, 8, current_timestamp, current_timestamp, 'System_Admin'),
       (2, 9, current_timestamp, current_timestamp, 'System_Admin'),
       (3, 5, current_timestamp, current_timestamp, 'System_Admin');