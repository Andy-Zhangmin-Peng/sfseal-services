-- auto Generated on 2020-03-25
-- DROP TABLE IF EXISTS sfseal_company.import_record;
CREATE TABLE sfseal_company.import_record(
	create_date timestamp with time zone NOT NULL,
	last_update_date timestamp with time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	import_id bigserial PRIMARY KEY NOT NULL,
	import_count bigint NOT NULL
);


COMMENT ON COLUMN sfseal_company.import_record.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.import_record.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.import_record.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.import_record.import_id IS '导入编号';
COMMENT ON COLUMN sfseal_company.import_record.import_count IS '导入数量';
