-- auto Generated on 2020-03-05
-- DROP TABLE IF EXISTS sfseal_company.company_lock_xref;
CREATE TABLE sfseal_company.company_lock_xref(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	xref_id bigserial PRIMARY KEY NOT NULL,
	company_id bigint NOT NULL,
	rfid varchar (200)  NOT NULL,
	is_active boolean NOT NULL,
    dispense_record_id bigint not null
);


COMMENT ON COLUMN sfseal_company.company_lock_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.company_lock_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.company_lock_xref.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.company_lock_xref.xref_id IS 'xrefId';
COMMENT ON COLUMN sfseal_company.company_lock_xref.company_id IS 'companyId';
COMMENT ON COLUMN sfseal_company.company_lock_xref.rfid IS 'rfid';
COMMENT ON COLUMN sfseal_company.company_lock_xref.is_active IS 'isActive';
COMMENT ON COLUMN sfseal_company.company_lock_xref.dispense_record_id IS '分发记录ID';


CREATE TABLE sfseal_company.company_lock_xref_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.company_lock_xref
);
