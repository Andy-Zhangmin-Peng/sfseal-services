-- auto Generated on 2020-03-02
-- DROP TABLE IF EXISTS sfseal_company.department;
CREATE TABLE sfseal_company.department(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	department_id bigserial PRIMARY KEY NOT NULL,
	department_name varchar (100) NOT NULL,
	department_phone_number varchar (100) NOT NULL,
	department_administrator varchar (100) NOT NULL
);


COMMENT ON COLUMN sfseal_company.department.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.department.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.department.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.department.department_id IS '部门ID';
COMMENT ON COLUMN sfseal_company.department.department_name IS '部门名称';
COMMENT ON COLUMN sfseal_company.department.department_phone_number IS '部门电话';
COMMENT ON COLUMN sfseal_company.department.department_administrator IS '部门负责人';

CREATE TABLE sfseal_company.department_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.department
);
