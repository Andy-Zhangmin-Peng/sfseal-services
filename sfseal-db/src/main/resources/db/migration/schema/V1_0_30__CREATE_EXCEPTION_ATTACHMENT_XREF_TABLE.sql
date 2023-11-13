-- auto Generated on 2020-03-27
-- DROP TABLE IF EXISTS sfseal_company.exceptional_situation_attachment_xref;
CREATE TABLE sfseal_company.exceptional_situation_attachment_xref(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	xref_id bigserial PRIMARY KEY NOT NULL,
	attachment_id bigint NOT NULL,
	exception_situation_id bigint NOT NULL
);


COMMENT ON COLUMN sfseal_company.exceptional_situation_attachment_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.exceptional_situation_attachment_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.exceptional_situation_attachment_xref.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.exceptional_situation_attachment_xref.xref_id IS '关联关系ID';
COMMENT ON COLUMN sfseal_company.exceptional_situation_attachment_xref.attachment_id IS '附件ID';
COMMENT ON COLUMN sfseal_company.exceptional_situation_attachment_xref.exception_situation_id IS '异常报告ID';
