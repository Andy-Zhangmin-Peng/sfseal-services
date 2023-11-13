--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_user.maintain_role_history() RETURNS TRIGGER AS
$maintain_role_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_user.role_history (action_type,
                                              role_id,
                                              role_name,
                                              role_display_name,
                                              usage,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('C',
                NEW.role_id,
                NEW.role_name,
                NEW.role_display_name,
                NEW.usage,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_user.role_history (action_type,
                                              role_id,
                                              role_name,
                                              role_display_name,
                                              usage,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('U',
                NEW.role_id,
                NEW.role_name,
                NEW.role_display_name,
                NEW.usage,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_user.role_history (action_type,
                                              role_id,
                                              role_name,
                                              role_display_name,
                                              create_date,
                                              last_update_date,
                                              last_update_user)
        VALUES ('D',
                OLD.role_id,
                OLD.role_name,
                OLD.role_display_name,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_role_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS role_trigger ON sfseal_user.role;

-- Create trigger
CREATE TRIGGER role_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_user.role
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_user.maintain_role_history();
