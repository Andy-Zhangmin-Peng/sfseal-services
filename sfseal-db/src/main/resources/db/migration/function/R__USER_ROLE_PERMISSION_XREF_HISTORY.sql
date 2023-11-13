--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_user.maintain_role_permission_xref_history() RETURNS TRIGGER AS
$maintain_role_permission_xref_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_user.role_permission_xref_history (action_type,
                                                              xref_id,
                                                              role_id,
                                                              permission_id,
                                                              create_date,
                                                              last_update_date,
                                                              last_update_user)
        VALUES ('C',
                NEW.xref_id,
                NEW.role_id,
                NEW.permission_id,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_user.role_permission_xref_history (action_type,
                                                              xref_id,
                                                              role_id,
                                                              permission_id,
                                                              create_date,
                                                              last_update_date,
                                                              last_update_user)
        VALUES ('U',
                NEW.xref_id,
                NEW.role_id,
                NEW.permission_id,
                NEW.create_date,
                NEW.last_update_date,
                NEW.last_update_user);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_user.role_permission_xref_history (action_type,
                                                              xref_id,
                                                              role_id,
                                                              permission_id,
                                                              create_date,
                                                              last_update_date,
                                                              last_update_user)
        VALUES ('D',
                OLD.xref_id,
                OLD.role_id,
                OLD.permission_id,
                OLD.create_date,
                current_timestamp,
                OLD.last_update_user);
    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_role_permission_xref_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS role_permission_xref_trigger ON sfseal_user.role_permission_xref;

-- Create trigger
CREATE TRIGGER role_permission_xref_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_user.role_permission_xref
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_user.maintain_role_permission_xref_history();
