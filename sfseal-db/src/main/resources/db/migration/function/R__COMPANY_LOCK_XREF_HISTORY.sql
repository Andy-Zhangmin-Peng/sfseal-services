--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_company.maintain_company_lock_xref_history() RETURNS TRIGGER AS
$maintain_company_lock_xref_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_company.company_lock_xref_history (action_type,
                                               xref_id,
                                               rfid,
                                               company_id,
                                                is_active,
                                                dispense_record_id,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('C',
                NEW.xref_id,
                NEW.rfid,
                NEW.company_id,
                NEW.is_active,
                NEW.dispense_record_id,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_company.company_lock_xref_history (action_type,
                                               xref_id,
                                               rfid,
                                               company_id,
                                               is_active,
                                               dispense_record_id,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('U',
                NEW.xref_id,
                NEW.rfid,
                NEW.company_id,
                NEW.is_active,
                NEW.dispense_record_id,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_company.company_lock_xref_history (action_type,
                                               xref_id,
                                                rfid,
                                               company_id,
                                               is_active,
                                               dispense_record_id,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('D',
                OLD.xref_id,
                OLD.rfid,
                OLD.company_id,
                OLD.is_active,
                OLD.dispense_record_id,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_company_lock_xref_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS company_lock_xref_trigger ON sfseal_company.company_lock_xref;

-- Create trigger
CREATE TRIGGER company_lock_xref_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_company.company_lock_xref
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_company.maintain_company_lock_xref_history();
