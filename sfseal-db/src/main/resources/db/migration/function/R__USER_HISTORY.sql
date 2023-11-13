--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_user.maintain_user_history() RETURNS TRIGGER AS
$maintain_user_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_user.user_history (action_type,
                                              user_id,
                                              name,
                                              account_id,
                                              address,
                                              mobile,
                                              is_active,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('C',
                NEW.user_id,
                NEW.name,
                NEW.account_id,
                NEW.address,
                NEW.mobile,
                NEW.is_active,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_user.user_history (action_type,
                                              user_id,
                                              name,
                                              account_id,
                                              address,
                                              mobile,
                                              is_active,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('U',
                NEW.user_id,
                NEW.name,
                NEW.account_id,
                NEW.address,
                NEW.mobile,
                NEW.is_active,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_user.user_history (action_type,
                                              user_id,
                                              name,
                                              account_id,
                                              address,
                                              mobile,
                                              is_active,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('D',
                OLD.user_id,
                OLD.name,
                OLD.account_id,
                OLD.address,
                OLD.mobile,
                OLD.is_active,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_user_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS user_trigger ON sfseal_user.user;

-- Create trigger
CREATE TRIGGER user_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_user.user
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_user.maintain_user_history();
