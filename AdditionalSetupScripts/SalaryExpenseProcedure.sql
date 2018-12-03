CREATE OR REPLACE PROCEDURE calc_salary_exp(yearly out NUMBER, monthly out NUMBER) AS 
BEGIN 
    SELECT SUM(SALARY) INTO yearly FROM EMPLOYEE;
    monthly := yearly/12; 
    
END calc_salary_exp;