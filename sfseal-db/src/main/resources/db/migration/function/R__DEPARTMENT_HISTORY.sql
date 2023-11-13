--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_company.maintain_department_history() RETURNS TRIGGER AS
$maintain_staff_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_company.department_history (action_type,
                                                department_id,
                                                department_name,
                                                department_administrator,
                                                department_phone_number,
                                                create_date,
                                                last_update_date,
                                                last_update_user)
        VALUES ('C',
                NEW.department_id,
                NEW.department_name,
                NEW.department_administrator,
                NEW.department_phone_number,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_company.department_history (action_type,
                                               department_id,
                                               department_name,
                                               department_administrator,
                                               department_phone_number,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('U',
                NEW.department_id,
                NEW.department_name,
                NEW.department_administrator,
                NEW.department_phone_number,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_company.department_history (action_type,
                                               department_id,
                                               department_name,
                                               department_administrator,
                                               department_phone_number,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('D',
                OLD.department_id,
                OLD.department_name,
                OLD.department_administrator,
                OLD.department_phone_number,
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
        EXISTS department_trigger ON sfseal_company.department;

-- Create trigger
CREATE TRIGGER department_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_company.department
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_company.maintain_department_history();
