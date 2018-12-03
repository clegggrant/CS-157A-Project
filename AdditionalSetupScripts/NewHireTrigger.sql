CREATE OR REPLACE TRIGGER new_hire 
BEFORE INSERT ON EMPLOYEE 
FOR EACH ROW 
BEGIN 
    SELECT emp_seq.NEXTVAL 
    INTO :new.EMPLOYEE_ID 
    FROM dual;
END; 