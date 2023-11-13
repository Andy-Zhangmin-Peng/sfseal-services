-- auto Generated on 2020-03-17
-- DROP TABLE IF EXISTS sfseal_company.work_order_address;
CREATE TABLE sfseal_company.work_order_address(
	create_date timestamp with time zone NOT NULL,
	last_update_date timestamp with time zone NOT NULL,
	last_update_user varchar (50) NOT NULL,
	address_id bigserial PRIMARY KEY NOT NULL,
	work_order_id bigint NOT NULL,
	address_type bigint NOT NULL ,
	address_detail varchar(300),
	lng_lat  POINT
);
CREATE INDEX work_order_address_work_order_id ON sfseal_company.work_order_address(work_order_id);

COMMENT ON COLUMN sfseal_company.work_order_address.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.work_order_address.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.work_order_address.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.work_order_address.address_id IS '运单地址ID';
COMMENT ON COLUMN sfseal_company.work_order_address.work_order_id IS '运单ID';

INSERT INTO sfseal_reference.reference_data(id, parent_id, "language", category, "key", display_key, display_order, description, attributes)
VALUES (30001, 0, 1, 'ADDRESS_TYPE', 'PRE_START_ADDRESS', '订单预计开始位置', 0, '订单预计开始位置', null ),
       (30002, 0, 1, 'ADDRESS_TYPE', 'PRE_END_ADDRESS', '订单预计结束位置', 0, '订单预计结束位置', null ),
       (30003, 0, 1, 'ADDRESS_TYPE', 'ACT_START_ADDRESS', '订单实际开始位置', 0, '订单实际开始位置', null ),
       (30004, 0, 1, 'ADDRESS_TYPE', 'ACT_END_ADDRESS', '订单实际结束位置', 0, '订单实际结束位置', null );
