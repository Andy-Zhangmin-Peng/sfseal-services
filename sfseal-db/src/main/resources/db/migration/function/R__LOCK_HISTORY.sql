--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_company.maintain_lock_history() RETURNS TRIGGER AS
$maintain_lock_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_company.lock_history (action_type,
                                              bar_code,
                                              box_id,
                                              status_code,
                                              rfid,
                                              lock_position,
                                              lock_user_id,
                                              unlock_user_id,
                                              tran_id,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('C',
                NEW.bar_code,
                NEW.box_id,
                NEW.status_code,
                NEW.rfid,
                NEW.lock_position,
                NEW.lock_user_id,
                NEW.unlock_user_id,
                NEW.tran_id,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_company.lock_history (action_type,
                                              bar_code,
                                              box_id,
                                              status_code,
                                              rfid,
                                              lock_position,
                                              lock_user_id,
                                              unlock_user_id,
                                                 tran_id,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('U',
                NEW.bar_code,
                NEW.box_id,
                NEW.status_code,
                NEW.rfid,
                NEW.lock_position,
                NEW.lock_user_id,
                NEW.unlock_user_id,
                NEW.tran_id,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_company.lock_history (action_type,
                                              bar_code,
                                              box_id,
                                              status_code,
                                              rfid,
                                              lock_position,
                                              lock_user_id,
                                              unlock_user_id,
                                                 tran_id,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('D',
                OLD.bar_code,
                OLD.box_id,
                OLD.status_code,
                OLD.rfid,
                OLD.lock_position,
                OLD.lock_user_id,
                OLD.unlock_user_id,
                OLD.tran_id,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_lock_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS lock_trigger ON sfseal_company.lock;

-- Create trigger
CREATE TRIGGER lock_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_company.lock
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_company.maintain_lock_history();
