-- auto Generated on 2020-03-17
-- DROP TABLE IF EXISTS sfseal_company.work_order_lock_xref;
CREATE TABLE sfseal_company.work_order_lock_xref(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (50) NOT NULL,
	xref_id bigserial PRIMARY KEY NOT NULL,
	work_order_id bigint NOT NULL,
	bar_code varchar(100) NOT NULL,
    is_active boolean not null ,
	CONSTRAINT work_order_lock_xref_work_order_id_fkey FOREIGN KEY (work_order_id)
        REFERENCES sfseal_company.work_order (work_order_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX work_order_lock_xref_work_order_id ON sfseal_company.work_order_lock_xref(work_order_id);

COMMENT ON COLUMN sfseal_company.work_order_lock_xref.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.work_order_lock_xref.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.work_order_lock_xref.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.work_order_lock_xref.xref_id IS '关联关系ID';
COMMENT ON COLUMN sfseal_company.work_order_lock_xref.work_order_id IS '工单ID';
COMMENT ON COLUMN sfseal_company.work_order_lock_xref.bar_code IS '电子封锁序列号';


CREATE TABLE sfseal_company.work_order_lock_xref_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.work_order_lock_xref
);
