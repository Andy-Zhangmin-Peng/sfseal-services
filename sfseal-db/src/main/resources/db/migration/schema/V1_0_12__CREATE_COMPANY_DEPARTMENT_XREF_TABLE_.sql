-- auto Generated on 2020-03-02
-- DROP TABLE IF EXISTS sfseal_company.company_department_xref;
CREATE TABLE sfseal_company.company_department_xref(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	xref_id bigserial PRIMARY KEY NOT NULL,
	department_id bigint NOT NULL,
	company_id bigint NOT NULL,
    CONSTRAINT company_department_xref_department_id_fkey foreign key (department_id)
        references sfseal_company.department (department_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT company_department_xref_company_id_fkey foreign key (company_id)
        references sfseal_company.company (company_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);


COMMENT ON COLUMN sfseal_company.company_department_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.company_department_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.company_department_xref.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.company_department_xref.xref_id IS '关系ID';
COMMENT ON COLUMN sfseal_company.company_department_xref.department_id IS '部门ID';
COMMENT ON COLUMN sfseal_company.company_department_xref.company_id IS '公司ID';

CREATE TABLE sfseal_company.company_department_xref_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.company_department_xref
);

