-- auto Generated on 2020-03-05
-- DROP TABLE IF EXISTS sfseal_company.lock;
CREATE TABLE sfseal_company.lock(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (200) NOT NULL,
	rfid varchar PRIMARY KEY NOT NULL,
	box_id varchar (100) NOT NULL,
	bar_code varchar (10) NOT NULL,
	status_code bigint NOT NULL
);
CREATE INDEX lock_rfid ON sfseal_company.lock(rfid);
CREATE INDEX ock_box_id_bar_code ON sfseal_company.lock(box_id,bar_code);

COMMENT ON COLUMN sfseal_company.lock.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.lock.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.lock.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.lock.rfid IS '芯片号';
COMMENT ON COLUMN sfseal_company.lock.box_id IS '盒条码';
COMMENT ON COLUMN sfseal_company.lock.bar_code IS '条形码号';
COMMENT ON COLUMN sfseal_company.lock.status_code IS '状态码';


CREATE TABLE sfseal_company.lock_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.lock
);

