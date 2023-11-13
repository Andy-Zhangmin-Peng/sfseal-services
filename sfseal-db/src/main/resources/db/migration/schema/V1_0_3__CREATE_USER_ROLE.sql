-- auto Generated on 2020-02-18
-- DROP TABLE IF EXISTS sfseal_user.role;
CREATE TABLE sfseal_user.role
(
    role_id           bigserial PRIMARY KEY       NOT NULL,
    role_name         varchar(100)                NOT NULL,
    usage             varchar(100)                NOT NULL,
    role_display_name varchar(100),
    role_desc         varchar(200),
    create_date       timestamp without time zone NOT NULL,
    last_update_date  timestamp without time zone NOT NULL,
    last_update_user  varchar(200)                NOT NULL
);


COMMENT ON COLUMN sfseal_user.role.role_id IS '角色ID';
COMMENT ON COLUMN sfseal_user.role.role_name IS '角色名';
COMMENT ON COLUMN sfseal_user.role.usage is '权限适用范围';
COMMENT ON COLUMN sfseal_user.role.role_display_name IS '角色显示名';
COMMENT ON COLUMN sfseal_user.role.role_desc IS '角色描述';
COMMENT ON COLUMN sfseal_user.role.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_user.role.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_user.role.last_update_user IS '上次更新人';


CREATE TABLE sfseal_user.role_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_user.role
);

INSERT INTO sfseal_user.role (role_id, role_name, usage, role_display_name, role_desc, create_date, last_update_date,
                              last_update_user)
VALUES (1, 'SYSTEM_ADMIN', 'system_user', '系统管理员', '系统管理员角色', current_timestamp, current_timestamp, 'System_Admin'),
       (2, 'COMPANY_ADMIN', 'system_user', '企业管理员', '企业管理员角色', current_timestamp, current_timestamp, 'System_Admin'),
       (3, 'COMPANY_USER', 'end_user', '移动端用户', '移动端用户角色', current_timestamp, current_timestamp, 'System_Admin');