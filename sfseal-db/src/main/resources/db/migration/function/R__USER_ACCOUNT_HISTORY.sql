--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_user.maintain_account_history() RETURNS TRIGGER AS
$maintain_account_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_user.account_history (action_type,
                                                 account_id,
                                                 username,
                                                 password,
                                                 phone_number,
                                                 email,
                                                 create_date,
                                                 last_update_date,
                                                 last_update_user)
        VALUES ('C',
                NEW.account_id,
                NEW.username,
                NEW.password,
                NEW.phone_number,
                NEW.email,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_user.account_history (action_type,
                                                 account_id,
                                                 username,
                                                 password,
                                                 phone_number,
                                                 email,
                                                 create_date,
                                                 last_update_date,
                                                 last_update_user)
        VALUES ('U',
                NEW.account_id,
                NEW.username,
                NEW.password,
                NEW.phone_number,
                NEW.email,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_user.account_history (action_type,
                                                 account_id,
                                                 username,
                                                 password,
                                                 phone_number,
                                                 email,
                                                 create_date,
                                                 last_update_date,
                                                 last_update_user)
        VALUES ('D',
                OLD.account_id,
                OLD.username,
                OLD.password,
                OLD.phone_number,
                OLD.email,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_account_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS account_trigger ON sfseal_user.account;

-- Create trigger
CREATE TRIGGER account_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_user.account
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_user.maintain_account_history();
