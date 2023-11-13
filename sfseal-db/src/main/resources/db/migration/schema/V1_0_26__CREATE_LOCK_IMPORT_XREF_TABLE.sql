-- auto Generated on 2020-03-25
-- DROP TABLE IF EXISTS sfseal_company.lock_import_xref;
CREATE TABLE sfseal_company.lock_import_xref(
	create_date timestamp with time zone NOT NULL,
	last_update_date timestamp with time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	xref_id bigserial PRIMARY KEY NOT NULL,
	rfid varchar (200) NOT NULL,
	import_id bigint NOT NULL,
    CONSTRAINT lock_import_xref_import_id_fkey FOREIGN KEY (import_id)
        REFERENCES sfseal_company.import_record (import_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX lock_import_xref_rfid ON sfseal_company.lock_import_xref(rfid);

COMMENT ON COLUMN sfseal_company.lock_import_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.lock_import_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.lock_import_xref.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.lock_import_xref.xref_id IS '关系ID';
COMMENT ON COLUMN sfseal_company.lock_import_xref.rfid IS '电子封锁RFID';
COMMENT ON COLUMN sfseal_company.lock_import_xref.import_id IS '导入记录ID';
