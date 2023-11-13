
-- auto Generated on 2020-03-27
-- DROP TABLE IF EXISTS attachment;
CREATE TABLE sfseal_company.attachment(
	create_date timestamp with time zone NOT NULL,
	last_update_date timestamp with time zone NOT NULL,
	last_update_user varchar (50) NOT NULL,
	attachment_id bigserial PRIMARY KEY NOT NULL,
	attachment_code text NOT NULL,
	attachment_name varchar (200) NOT NULL,
	attachment_type bigint
);


COMMENT ON COLUMN sfseal_company.attachment.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.attachment.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.attachment.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.attachment.attachment_id IS '附件编号';
COMMENT ON COLUMN sfseal_company.attachment.attachment_code IS '附件Base64 Code';
COMMENT ON COLUMN sfseal_company.attachment.attachment_name IS '附件名称';
COMMENT ON COLUMN sfseal_company.attachment.attachment_type IS '附件类型';

INSERT INTO sfseal_reference.reference_data(id, parent_id, "language", category, "key", display_key, display_order, description, attributes)
VALUES (60001, 0, 1, 'ATTACHMNET_TYPE', 'WORK_ORDER', '运单照片', 0, '运单照片', null ),
       (60002, 0, 1, 'ATTACHMNET_TYPE', 'LOCK', '施封照片', 0, '施封照片', null ),
       (60003, 0, 1, 'ATTACHMNET_TYPE', 'UNLOCK', '拆封照片', 0, '拆封照片', null ),
       (60004, 0, 1, 'ATTACHMNET_TYPE', 'EXCEPTION', '异常照片', 0, '异常照片', null );
