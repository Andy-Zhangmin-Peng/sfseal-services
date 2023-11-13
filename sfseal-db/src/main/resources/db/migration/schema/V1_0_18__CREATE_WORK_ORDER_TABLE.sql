-- auto Generated on 2020-03-17
-- DROP TABLE IF EXISTS sfseal_company.work_order;
CREATE TABLE sfseal_company.work_order(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	work_order_id bigserial PRIMARY KEY NOT NULL,
	company_id bigint NOT NULL,
	status_code bigint NOT NULL,
	owner varchar (200) NOT NULL,
    carriera varchar (200) NOT NULL,
	start_time timestamp without time zone,
	end_time timestamp without time zone,
	product_detail varchar (300),
	attachment_id bigint
);
CREATE INDEX work_order_company_id ON sfseal_company.work_order(company_id);
CREATE INDEX work_order_start_time_end_time ON sfseal_company.work_order(start_time,end_time);

COMMENT ON COLUMN sfseal_company.work_order.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.work_order.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.work_order.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.work_order.work_order_id IS '工单编号';
COMMENT ON COLUMN sfseal_company.work_order.company_id IS '所属公司ID';
COMMENT ON COLUMN sfseal_company.work_order.status_code IS '运单状态码';
COMMENT ON COLUMN sfseal_company.work_order.owner IS '货主姓名';
COMMENT ON COLUMN sfseal_company.work_order.carriera IS '承运人';
COMMENT ON COLUMN sfseal_company.work_order.start_time IS '订单开始时间';
COMMENT ON COLUMN sfseal_company.work_order.end_time IS '订单结束时间';
COMMENT ON COLUMN sfseal_company.work_order.product_detail IS '货物明细';
COMMENT ON COLUMN sfseal_company.work_order.attachment_id IS '运单附件ID';

CREATE TABLE sfseal_company.work_order_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.work_order
);