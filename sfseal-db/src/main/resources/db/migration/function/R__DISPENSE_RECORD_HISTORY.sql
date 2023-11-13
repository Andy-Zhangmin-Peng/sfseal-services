--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_company.maintain_dispense_record_history() RETURNS TRIGGER AS
$maintain_dispense_record_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_company.dispense_record_history (action_type,
                                              dispense_record_id,
                                              company_id,
                                              dispense_count,
                                              is_active,
                                              accpeted,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('C',
                NEW.dispense_record_id,
                NEW.company_id,
                NEW.dispense_count,
                NEW.is_active,
                NEW.accpeted,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_company.dispense_record_history (action_type,
                                              dispense_record_id,
                                              company_id,
                                              dispense_count,
                                              is_active,
                                              accpeted,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('U',
                NEW.dispense_record_id,
                NEW.company_id,
                NEW.dispense_count,
                NEW.is_active,
                NEW.accpeted,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_company.dispense_record_history (action_type,
                                              dispense_record_id,
                                              company_id,
                                              dispense_count,
                                              is_active,
                                              accpeted,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('D',
                OLD.dispense_record_id,
                OLD.company_id,
                OLD.dispense_count,
                OLD.is_active,
                OLD.accpeted,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_dispense_record_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS dispense_record_trigger ON sfseal_company.dispense_record;

-- Create trigger
CREATE TRIGGER dispense_record_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_company.dispense_record
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_company.maintain_dispense_record_history();
