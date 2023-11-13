--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_company.maintain_company_history() RETURNS TRIGGER AS
$maintain_staff_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_company.company_history (action_type,
                                               company_id,
                                               company_name,
                                               email,
                                               phone_number,
                                               address,
                                               
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('C',
                NEW.company_id,
                NEW.company_name,
                NEW.email,
                NEW.phone_number,
                NEW.address,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_company.company_history (action_type,
                                               company_id,
                                               company_name,
                                               email,
                                               phone_number,
                                               address,
                                               
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('U',
                NEW.company_id,
                NEW.company_name,
                NEW.email,
                NEW.phone_number,
                NEW.address,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_company.company_history (action_type,
                                               company_id,
                                               company_name,
                                               email,
                                               phone_number,
                                               address,
                                               email,
                                               create_date,
                                               last_update_date,
                                               last_update_user)
        VALUES ('D',
                OLD.company_id,
                OLD.company_name,
                OLD.email,
                OLD.phone_number,
                OLD.address,
                OLD.email,
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
        EXISTS company_trigger ON sfseal_company.company;

-- Create trigger
CREATE TRIGGER company_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_company.company
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_company.maintain_company_history();
