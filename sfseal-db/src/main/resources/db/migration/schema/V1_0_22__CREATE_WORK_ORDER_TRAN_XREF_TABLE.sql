-- auto Generated on 2020-03-17
-- DROP TABLE IF EXISTS sfseal_company.work_order_transport_xref;
CREATE TABLE sfseal_company.work_order_transport_xref(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (50) NOT NULL,
	xref_id bigserial PRIMARY KEY NOT NULL,
	work_order_id bigint NOT NULL,
	tran_id bigint NOT NULL,
    is_active boolean not null ,
    CONSTRAINT work_order_lock_xref_work_order_id_fkey FOREIGN KEY (work_order_id)
        REFERENCES sfseal_company.work_order (work_order_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX work_order_transport_xref_work_order_id ON sfseal_company.work_order_transport_xref(work_order_id);

COMMENT ON COLUMN sfseal_company.work_order_transport_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.work_order_transport_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.work_order_transport_xref.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.work_order_transport_xref.xref_id IS 'xrefId';
COMMENT ON COLUMN sfseal_company.work_order_transport_xref.work_order_id IS 'workOrderId';
COMMENT ON COLUMN sfseal_company.work_order_transport_xref.tran_id IS 'tranId';


CREATE TABLE sfseal_company.work_order_transport_xref_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.work_order_transport_xref
);


INSERT INTO sfseal_reference.reference_data(id, parent_id, "language", category, "key", display_key, display_order, description, attributes)
VALUES (20001, 0, 1, 'WORK_ORDER_STATUS', 'NOTBEGIN', '未开始', 0, '库存', null ),
       (20002, 0, 1, 'WORK_ORDER_STATUS', 'INPROGRESS', '运输中', 0, '已分发', null ),
       (20003, 0, 1, 'WORK_ORDER_STATUS', 'FINISHED', '已结束', 0, '未使用', null );

alter table sfseal_company.work_order_transport_xref add constraint work_order_transport unique(work_order_id,tran_id);
alter table sfseal_company.work_order_address add constraint work_order_address_address_id_type_order_id unique(work_order_id,address_id,address_type);
alter table sfseal_company.work_order_lock_xref add constraint work_order_lock unique(work_order_id,bar_code);
alter table sfseal_company.transport add constraint transport_company_id unique(transprort_id,company_id);