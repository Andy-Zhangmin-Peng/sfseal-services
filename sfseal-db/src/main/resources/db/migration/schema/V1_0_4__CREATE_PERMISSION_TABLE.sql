-- auto Generated on 2020-02-18
-- DROP TABLE IF EXISTS sfseal_user.permission;
CREATE TABLE sfseal_user.permission
(
    permission_id           bigserial PRIMARY KEY    NOT NULL,
    permission_name         varchar(100)             NOT NULL,
    permission_display_name varchar(100)             NOT NULL,
    description             varchar(200)             NOT NULL,
    create_date             timestamp with time zone NOT NULL,
    last_update_date        timestamp with time zone NOT NULL,
    last_update_user        varchar(200)             NOT NULL
);


COMMENT ON COLUMN sfseal_user.permission.permission_id IS '权限ID';
COMMENT ON COLUMN sfseal_user.permission.permission_name IS '权限名称';
COMMENT ON COLUMN sfseal_user.permission.permission_display_name IS '权限显示名称';
COMMENT ON COLUMN sfseal_user.permission.description IS '权限描述';
COMMENT ON COLUMN sfseal_user.permission.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_user.permission.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_user.permission.last_update_user IS '上次更新人';

CREATE TABLE sfseal_user.permission_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_user.permission
);

insert into sfseal_user.permission (permission_id, permission_name, permission_display_name, description, create_date,
                                    last_update_date, last_update_user)
VALUES (1, 'ALL_PERMISSION', '所有操作权限', '可以执行系统所有操作端权限', current_timestamp, current_timestamp, 'System_Admin'),
       (2, 'CREATE_COMPANY_PERMISSION', '创建公司信息权限', '可以添加公司的权限', current_timestamp, current_timestamp,
        'System_Admin'),
       (3, 'CREATE_SYSTEM_USER_PERMISSION', '创建系统用户权限', '可以创建系统用户权限', current_timestamp, current_timestamp,
        'System_Admin'),
       (4, 'CREATE_APP_USER_PERMISSION', '创建移动端用户权限', '可以创建移动端用户权限', current_timestamp, current_timestamp,
        'System_Admin'),
       (5, 'VIEW_USER_PERMISSION', '查看用户权限', '查看用户权限', current_timestamp, current_timestamp, 'System_Admin'),
       (6, 'VIEW_STAFF_PERMISSION', '查看管理员权限', '查看管理员权限', current_timestamp, current_timestamp,
        'System_Admin'),
       (7, 'UPDATE_STAFF_PERMISSION', '更新管理员权限', '更新管理员权限', current_timestamp, current_timestamp,
        'System_Admin'),
       (8, 'UPDATE_COMPANY_PERMISSION', '更新公司信息权限', '更新公司信息权限', current_timestamp, current_timestamp,
        'System_Admin'),
       (9, 'DEPARTMENT_ADMIN_PERMISSION', '部门管理权限', '部门管理权限', current_timestamp, current_timestamp,
        'System_Admin');