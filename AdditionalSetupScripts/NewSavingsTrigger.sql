CREATE OR REPLACE TRIGGER new_savings 
BEFORE INSERT ON SAVINGS
FOR EACH ROW
DECLARE
    v_next number(10);
    v_today date;
BEGIN 
    v_next := acct_seq.NEXTVAL;
    v_today := sysdate;
    
    SELECT v_next
    INTO :new.ACCT_ID
    FROM dual;
    
    INSERT INTO ACCOUNT(ACCT_ID, EMPLOYEE_ID, SSN, BALANCE, OPEN_DATE) VALUES
    (v_next, :new.EMPLOYEE_ID, :new.SSN, :new.BALANCE, v_today);
END;