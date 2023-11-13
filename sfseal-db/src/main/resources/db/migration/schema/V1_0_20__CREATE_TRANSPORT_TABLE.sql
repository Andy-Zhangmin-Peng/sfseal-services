-- auto Generated on 2020-03-17
-- DROP TABLE IF EXISTS sfseal_company.transport;
CREATE TABLE sfseal_company.transport(
	create_date timestamp without time zone NOT NULL,
	last_update_date timestamp without time zone NOT NULL,
	last_update_user varchar (50) NOT NULL,
	id bigserial PRIMARY KEY NOT NULL,
	transprort_id varchar (50) NOT NULL,
	company_id bigint NOT NULL,
	CONSTRAINT transport_company_id_fkey FOREIGN KEY (company_id)
        REFERENCES sfseal_company.company (company_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE UNIQUE INDEX  transport_transprort_id_company_id ON sfseal_company.transport(transprort_id,company_id);
COMMENT ON COLUMN sfseal_company.transport.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.transport.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.transport.last_update_user IS '上次更新人';
COMMENT ON COLUMN sfseal_company.transport.id IS 'id';
COMMENT ON COLUMN sfseal_company.transport.transprort_id IS '运输载体ID';
COMMENT ON COLUMN sfseal_company.transport.company_id IS '运输载体服务过的公司ID';
