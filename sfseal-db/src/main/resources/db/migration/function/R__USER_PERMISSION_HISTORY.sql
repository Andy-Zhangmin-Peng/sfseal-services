--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_user.maintain_permission_history() RETURNS TRIGGER AS
$maintain_permission_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_user.permission_history (action_type,
                                                    permission_id,
                                                    permission_name,
                                                    permission_display_name,
                                                    description,
                                                    create_date,
                                                    last_update_date,
                                                    last_update_user)
        VALUES ('C',
                NEW.permission_id,
                NEW.permission_name,
                NEW.permission_display_name,
                NEW.description,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_user.permission_history (action_type,
                                                    permission_id,
                                                    permission_name,
                                                    permission_display_name,
                                                    description,
                                                    create_date,
                                                    last_update_date,
                                                    last_update_user)
        VALUES ('U',
                NEW.permission_id,
                NEW.permission_name,
                NEW.permission_display_name,
                NEW.description,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_user.permission_history (action_type,
                                                    permission_id,
                                                    permission_name,
                                                    permission_display_name,
                                                    description,
                                                    create_date,
                                                    last_update_date,
                                                    last_update_user)
        VALUES ('D',
                OLD.permission_id,
                OLD.permission_name,
                OLD.permission_display_name,
                OLD.description,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_permission_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS permission_trigger ON sfseal_user.permission;

-- Create trigger
CREATE TRIGGER permission_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_user.permission
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_user.maintain_permission_history();
