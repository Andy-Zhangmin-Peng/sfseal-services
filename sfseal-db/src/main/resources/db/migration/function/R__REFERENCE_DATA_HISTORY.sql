--Create function to maintain history table
CREATE
    OR REPLACE FUNCTION sfseal_reference.maintain_reference_data_history() RETURNS TRIGGER AS
$maintain_reference_data_history$
BEGIN
    -- When insert happened
    IF
        (tg_op = 'INSERT') THEN--insert record into history table
        INSERT INTO sfseal_reference.reference_data_history (action_type,
                                                 id,
                                                 parent_id,
                                                 language,
                                                 category,
                                                 key,
                                                 display_key,
                                                 display_order,
                                                 description,
                                                 is_active,
                                                 attributes,
                                                 last_update_date)
            VALUES ('C',
                    NEW.id,
                    NEW.parent_id,
                    NEW.language,
                    NEW.category,
                    NEW.key,
                    NEW.display_key,
                    NEW.display_order,
                    NEW.description,
                    NEW.is_active,
                    NEW.attributes,
                    NEW.last_update_date);

    END IF;
    -- When update happened
    IF
        (tg_op = 'UPDATE') THEN--insert record into history table
        INSERT INTO sfseal_reference.reference_data_history (action_type,
                                                 id,
                                                 parent_id,
                                                 language,
                                                 category,
                                                 key,
                                                 display_key,
                                                 display_order,
                                                 description,
                                                 is_active,
                                                 attributes,
                                                 last_update_date)
            VALUES ('U',
                    NEW.id,
                    NEW.parent_id,
                    NEW.language,
                    NEW.category,
                    NEW.key,
                    NEW.display_key,
                    NEW.display_order,
                    NEW.description,
                    NEW.is_active,
                    NEW.attributes,
                    NEW.last_update_date);

    END IF;
    --When delete happened
    IF
        (tg_op = 'DELETE') THEN--insert record into history table
        INSERT INTO sfseal_reference.reference_data_history (action_type,
                                                 id,
                                                 parent_id,
                                                 language,
                                                 category,
                                                 key,
                                                 display_key,
                                                 display_order,
                                                 description,
                                                 is_active,
                                                 attributes,
                                                 last_update_date)
            VALUES ('D',
                    OLD.id,
                    OLD.parent_id,
                    OLD.language,
                    OLD.category,
                    OLD.key,
                    OLD.display_key,
                    OLD.display_order,
                    OLD.description,
                    OLD.is_active,
                    OLD.attributes,
                    current_timestamp);

    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_reference_data_history$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS reference_data_trigger ON sfseal_reference.reference_data;

-- Create trigger
CREATE TRIGGER reference_data_trigger
    AFTER INSERT
        OR UPDATE
        OR DELETE
    ON sfseal_reference.reference_data
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_reference.maintain_reference_data_history();
