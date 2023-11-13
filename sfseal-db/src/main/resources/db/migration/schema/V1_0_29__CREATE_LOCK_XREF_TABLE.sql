-- auto Generated on 2020-03-27
-- DROP TABLE IF EXISTS sfseal_company.lock_attachment_xref;
CREATE TABLE sfseal_company.lock_attachment_xref(
	create_date timestamp with time zone NOT NULL,
	last_update_date timestamp with time zone NOT NULL,
	last_update_user varchar (50) NOT NULL,
	xref_id bigserial PRIMARY KEY NOT NULL,
	rfid varchar (50) NOT NULL,
	attachment_id bigint NOT NULL
);
CREATE INDEX lock_attachment_xref_rfid ON sfseal_company.lock_attachment_xref(rfid);

COMMENT ON COLUMN sfseal_company.lock_attachment_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.lock_attachment_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.lock_attachment_xref.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.lock_attachment_xref.xref_id IS '关联关系ID';
COMMENT ON COLUMN sfseal_company.lock_attachment_xref.rfid IS '电子封锁RFID';
COMMENT ON COLUMN sfseal_company.lock_attachment_xref.attachment_id IS '附件ID';
