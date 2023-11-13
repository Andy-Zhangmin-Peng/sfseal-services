
CREATE
    OR REPLACE FUNCTION sfseal_company.maintain_company_lock_xref() RETURNS TRIGGER AS
$maintain_company_lock_xref$
BEGIN

    -- When update happened
    IF
        (tg_op = 'UPDATE' and NEW.is_active = false) THEN--insert record into history table
        update sfseal_company.lock
        set status_code = 10001
        where rfid = old.rfid;

    END IF;
    IF
        (tg_op = 'UPDATE' and NEW.is_active = true) THEN--insert record into history table
        update sfseal_company.lock
        set status_code = 10002
        where rfid = old.rfid;

    END IF;
    RETURN NULL;
END;

-- Indicate language
$maintain_company_lock_xref$ LANGUAGE plpgsql;
-- Drop trigger
DROP TRIGGER
    IF
        EXISTS company_lock_xref_trigger ON sfseal_company.company_lock_xref;

-- Create trigger
CREATE TRIGGER company_lock_xref_trigger
    AFTER  UPDATE
    ON sfseal_company.company_lock_xref
    FOR EACH ROW
EXECUTE PROCEDURE sfseal_company.maintain_company_lock_xref();
