create table sfseal_company.dispense_record(
    dispense_record_id bigserial PRIMARY KEY not null ,
    company_id bigint not null ,
    dispense_count bigint not null ,
    is_active boolean not null ,
    accpeted boolean not null ,
    create_date timestamp without time zone NOT NULL,
    last_update_date timestamp without time zone NOT NULL,
    last_update_user varchar (200) NOT NULL,
    CONSTRAINT dispense_record_company_id_fkey foreign key (company_id)
    references sfseal_company.company (company_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

COMMENT ON COLUMN sfseal_company.dispense_record.dispense_record_id IS '分发记录ID';
COMMENT ON COLUMN sfseal_company.dispense_record.company_id IS '公司ID';
COMMENT ON COLUMN sfseal_company.dispense_record.dispense_count IS '分发数量';
COMMENT ON COLUMN sfseal_company.dispense_record.accpeted IS '是否被接收';
COMMENT ON COLUMN sfseal_company.dispense_record.create_date IS '创建时间';
COMMENT ON COLUMN sfseal_company.dispense_record.last_update_date IS '上次更新时间';
COMMENT ON COLUMN sfseal_company.dispense_record.last_update_user IS '上次更新人';

CREATE TABLE sfseal_company.dispense_record_history
(
    history_id  BIGSERIAL PRIMARY KEY,
    action_type CHAR(1) NOT NULL,
    LIKE sfseal_company.dispense_record
);
