--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_company.maintain_work_order_history() RETURNS TRIGGER AS
$maintain_work_order_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_company.work_order_history (action_type,
                                               work_order_id,
                                               status_code,
                                               company_id,
                                               owner,
                                               start_time,
                                               end_time,
                                               product_detail,
                                               attachment_id,
                                               carriera,
                                               temp_carriera,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('C',
                NEW.work_order_id,
                NEW.status_code,
                NEW.company_id,
                NEW.owner,
                NEW.start_time,
                NEW.end_time,
                NEW.product_detail,
                NEW.attachment_id,
                NEW.carriera,
                NEW.temp_carriera,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_company.work_order_history (action_type,
                                               work_order_id,
                                               status_code,
                                               company_id,
                                               owner,
                                               start_time,
                                               end_time,
                                               product_detail,
                                               attachment_id,
                                               carriera,
                                               temp_carriera,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('U',
                NEW.work_order_id,
                NEW.status_code,
                NEW.company_id,
                NEW.owner,
                NEW.start_time,
                NEW.end_time,
                NEW.product_detail,
                NEW.attachment_id,
                NEW.carriera,
                NEW.temp_carriera,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_company.work_order_history (action_type,
                                               work_order_id,
                                               status_code,
                                               company_id,
                                               owner,
                                               start_time,
                                               end_time,
                                               product_detail,
                                               attachment_id,
                                               carriera,
                                               temp_carriera,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('D',
                OLD.work_order_id,
                OLD.status_code,
                OLD.company_id,
                OLD.owner,
                OLD.start_time,
                OLD.end_time,
                OLD.product_detail,
                OLD.attachment_id,
                OLD.carriera,
                OLD.temp_carriera,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_work_order_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS work_order_trigger ON sfseal_company.work_order;

-- Create trigger
CREATE TRIGGER work_order_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_company.work_order
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_company.maintain_work_order_history();
