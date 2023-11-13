--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_user.maintain_staff_history() RETURNS TRIGGER AS
$maintain_staff_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_user.staff_history (action_type,
                                               account_id,
                                               name,
                                               mobile,
                                               office_phone_number,
                                               address,
                                               is_active,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('C',
                NEW.account_id,
                NEW.name,
                NEW.mobile,
                NEW.office_phone_number,
                NEW.address,
                NEW.is_active,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_user.staff_history (action_type,
                                               account_id,
                                               name,
                                               mobile,
                                               office_phone_number,
                                               address,
                                               is_active,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('U',
                NEW.account_id,
                NEW.name,
                NEW.mobile,
                NEW.office_phone_number,
                NEW.address,
                NEW.is_active,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_user.staff_history (action_type,
                                               account_id,
                                               name,
                                               mobile,
                                               office_phone_number,
                                               address,
                                               mobile,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('D',
                OLD.account_id,
                OLD.name,
                OLD.mobile,
                OLD.office_phone_number,
                OLD.address,
                OLD.mobile,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_staff_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS staff_trigger ON sfseal_user.staff;

-- Create trigger
CREATE TRIGGER staff_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_user.staff
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_user.maintain_staff_history();
