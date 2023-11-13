--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_user.maintain_account_role_xref_history() RETURNS TRIGGER AS
$maintain_account_role_xref_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_user.account_role_xref_history (action_type,
                                                           xref_id,
                                                           account_id,
                                                           role_id,
                                                           create_date,
                                                           last_update_date,
                                                           last_update_user)
        VALUES ('C',
                NEW.xref_id,
                NEW.account_id,
                NEW.role_id,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_user.account_role_xref_history (action_type,
                                                           xref_id,
                                                           account_id,
                                                           role_id,
                                                           create_date,
                                                           last_update_date,
                                                           last_update_user)
        VALUES ('U',
                NEW.xref_id,
                NEW.account_id,
                NEW.role_id,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_user.account_role_xref_history (action_type,
                                                           xref_id,
                                                           account_id,
                                                           role_id,
                                                           create_date,
                                                           last_update_date,
                                                           last_update_user)
        VALUES ('D',
                OLD.xref_id,
                OLD.account_id,
                OLD.role_id,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_account_role_xref_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS account_role_xref_trigger ON sfseal_user.account_role_xref;

-- Create trigger
CREATE TRIGGER account_role_xref_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_user.account_role_xref
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_user.maintain_account_role_xref_history();
