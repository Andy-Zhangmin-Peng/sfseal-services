-- auto Generated on 2020-03-21
-- DROP TABLE IF EXISTS sfseal_company.exceptional_situation;
CREATE TABLE sfseal_company.exceptional_situation(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	exception_id bigserial PRIMARY KEY NOT NULL,
	exception_level bigint NOT NULL,
	exception_type_code bigint NOT NULL,
	exception_message varchar (300) NOT NULL,
	report_address_id bigint NOT NULL,
	report_user_id bigint NOT NULL,
    work_order_id bigint NOT NULL,
    company_id bigint NOT NULL,
    tran_id bigint NOT NULL,
	bar_code varchar (100) NOT NULL
);
CREATE INDEX exceptional_situation_bar_code ON sfseal_company.exceptional_situation(bar_code);

COMMENT ON COLUMN sfseal_company.exceptional_situation.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.exceptional_situation.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.exceptional_situation.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.exceptional_situation.exception_id IS '异常编号';
COMMENT ON COLUMN sfseal_company.exceptional_situation.exception_level IS '异常级别';
COMMENT ON COLUMN sfseal_company.exceptional_situation.exception_type_code IS '异常类型';
COMMENT ON COLUMN sfseal_company.exceptional_situation.exception_message IS '异常描述';
COMMENT ON COLUMN sfseal_company.exceptional_situation.report_address_id IS '上报异常地址ID';
COMMENT ON COLUMN sfseal_company.exceptional_situation.report_user_id IS '上报异常人ID';
COMMENT ON COLUMN sfseal_company.exceptional_situation.work_order_id IS '工单ID';
COMMENT ON COLUMN sfseal_company.exceptional_situation.company_id IS '公司ID';
COMMENT ON COLUMN sfseal_company.exceptional_situation.bar_code IS '电子封锁序列号';

INSERT INTO sfseal_reference.reference_data(id, parent_id, "language", category, "key", display_key, display_order, description, attributes)
VALUES (40001, 0, 1, 'EXCEPTION_LEVEL', 'INFO', '通知', 0, '通知', null ),
       (40002, 0, 1, 'EXCEPTION_LEVEL', 'WARNING', '警告', 0, '警告', null ),
       (40003, 0, 1, 'EXCEPTION_LEVEL', 'ERROR', '严重', 0, '严重', null );

INSERT INTO sfseal_reference.reference_data(id, parent_id, "language", category, "key", display_key, display_order, description, attributes)
VALUES (50001, 0, 1, 'EXCEPTION_TYPE', 'INFO_ERROR', '与工单信息不符', 0, '与工单信息不符', null ),
       (50002, 0, 1, 'EXCEPTION_TYPE', 'ADDRESS_ERROR', '位置不符', 0, '位置不符', null ),
       (50003, 0, 1, 'EXCEPTION_TYPE', 'LOCK_BREAK', '锁损坏', 0, '锁损坏', null );