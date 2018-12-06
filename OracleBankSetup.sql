/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     11/30/2018 9:28:18 AM                        */
/*==============================================================*/

CREATE USER Client IDENTIFIED BY password;

GRANT CONNECT, CREATE TABLE, CREATE VIEW, CREATE PROCEDURE, CREATE TRIGGER, CREATE SEQUENCE to Client;

GRANT UNLIMITED TABLESPACE TO Client;

CONNECT Client/password;

ALTER SESSION SET CURRENT_SCHEMA = Client;

alter table TRANSACTION_HISTORY
   drop constraint FK_TRANSACT_TRANSACTI_ACCOUNT;

drop index EMPLOYEE_ACCOUNT_FK;

drop index HAS_ACCOUNT_FK;

drop table ACCOUNT cascade constraints;

drop table CHECKING cascade constraints;

drop table CREDIT_CARD cascade constraints;

drop table CUSTOMER cascade constraints;

drop table EMPLOYEE cascade constraints;

drop table LOAN cascade constraints;

drop table SAVINGS cascade constraints;

drop index TRANSACTION_FK;

drop table TRANSACTION_HISTORY cascade constraints;

/*==============================================================*/
/* Table: CUSTOMER                                              */
/*==============================================================*/
create table CUSTOMER 
(
   SSN                  CHAR(11)             not null,
   FIRST_NAME           VARCHAR2(15)         not null,
   LAST_NAME            VARCHAR2(15)         not null,
   ADDR                 VARCHAR2(50)         not null,
   constraint PK_CUSTOMER primary key (SSN)
);

/*==============================================================*/
/* Table: EMPLOYEE                                              */
/*==============================================================*/
create table EMPLOYEE 
(
   EMPLOYEE_ID          NUMBER(10)             not null,
   POSITION             VARCHAR2(20)         not null,
   SALARY               NUMBER(10,2) not null CHECK(SALARY >= 31200),
   HIRE_DATE            DATE                 not null,
   FIRST_NAME           VARCHAR2(15)         not null,
   LAST_NAME            VARCHAR2(15)         not null,
   SOCIAL_SECURITY      CHAR(11)      not null unique,
   constraint PK_EMPLOYEE primary key (EMPLOYEE_ID)
);

/*==============================================================*/
/* Table: ACCOUNT                                               */
/*==============================================================*/
create table ACCOUNT 
(
   ACCT_ID              NUMBER(10)             not null,
   EMPLOYEE_ID          NUMBER(10) REFERENCES EMPLOYEE(EMPLOYEE_ID) ON DELETE SET NULL,
   SSN                  CHAR(11) REFERENCES CUSTOMER(SSN) ON DELETE CASCADE,
   BALANCE              NUMBER(10,2) not null CHECK(BALANCE >= 0),
   OPEN_DATE            DATE                 not null,
   constraint PK_ACCOUNT primary key (ACCT_ID)
);

/*==============================================================*/
/* Index: HAS_ACCOUNT_FK                                        */
/*==============================================================*/
create index HAS_ACCOUNT_FK on ACCOUNT (
   SSN ASC
);

/*==============================================================*/
/* Index: EMPLOYEE_ACCOUNT_FK                                   */
/*==============================================================*/
create index EMPLOYEE_ACCOUNT_FK on ACCOUNT (
   EMPLOYEE_ID ASC
);

/*==============================================================*/
/* Table: CHECKING                                              */
/*==============================================================*/
create table CHECKING 
(
   ACCT_ID              NUMBER(10) not null REFERENCES ACCOUNT(ACCT_ID) ON DELETE CASCADE,
   EMPLOYEE_ID          NUMBER(10),
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2) not null  CHECK(BALANCE >= 0),
   OPEN_DATE            DATE                 not null,
   WITHDRAWL_LIMIT      NUMBER(8,2)          not null,
   PURCHASE_LIMIT       NUMBER(10,2)         not null,
   ACCTTYPE             VARCHAR2(10),
   constraint PK_CHECKING primary key (ACCT_ID)
);

/*==============================================================*/
/* Table: CREDIT_CARD                                           */
/*==============================================================*/
create table CREDIT_CARD 
(
   ACCT_ID              NUMBER(10) not null REFERENCES ACCOUNT(ACCT_ID) ON DELETE CASCADE,
   EMPLOYEE_ID          NUMBER(10),
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2) not null CHECK(BALANCE >= 0),
   OPEN_DATE            DATE                 not null,
   LIMIT                NUMBER(10,2)         not null,
   DUE_DATE             DATE,
   MIN__PAYMENT         NUMBER(10,2),
   LAST_STATEMENT_BALANCE NUMBER(10,2),
   LAST_PAYMENT         NUMBER(10,2),
   INTEREST_RATE        NUMBER(10,2)          not null,
   ACCTTYPE             VARCHAR2(15),
   constraint PK_CREDIT_CARD primary key (ACCT_ID)
);

/*==============================================================*/
/* Table: LOAN                                                  */
/*==============================================================*/
create table LOAN 
(
   ACCT_ID              NUMBER(10) not null REFERENCES ACCOUNT(ACCT_ID) ON DELETE CASCADE,
   EMPLOYEE_ID          NUMBER(10),
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2) not null CHECK(BALANCE >=0),
   OPEN_DATE            DATE                 not null,
   INTEREST_RATE        NUMBER(4,3)          not null,
   TOTAL_LOAN           NUMBER(10,2)         not null,
   MONTHLY_PAYMENT      NUMBER(10,2)         not null,
   REMAINING_LOAN_TERM  INTEGER              not null,
   LOAN_TERM            INTEGER              not null,
   ACCTTYPE             VARCHAR2(15),
   constraint PK_LOAN primary key (ACCT_ID)
);

/*==============================================================*/
/* Index: REMAINING_LOAN_TERM                                   */
/*==============================================================*/
CREATE INDEX loan_indx ON Loan(remaining_loan_term ASC);

/*==============================================================*/
/* Table: SAVINGS                                               */
/*==============================================================*/
create table SAVINGS 
(
   ACCT_ID              NUMBER(10) not null REFERENCES ACCOUNT(ACCT_ID) ON DELETE CASCADE,
   EMPLOYEE_ID          NUMBER(10),
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2) not null CHECK(BALANCE >=0),
   OPEN_DATE            DATE                 not null,
   TRANSFER_LIMIT       INTEGER              not null,
   MINIMUM_BALANCE      NUMBER(10,2)         not null,
   INTEREST_RATE        NUMBER(4,3)          not null,
   ACCTTYPE             VARCHAR2(20),
   constraint PK_SAVINGS primary key (ACCT_ID)
);

/*==============================================================*/
/* Table: TRANSACTION_HISTORY                                   */
/*==============================================================*/
create table TRANSACTION_HISTORY 
(
   TRANSACTION_ID       NUMBER(10)           not null,
   ACCT_ID              NUMBER(10)           not null,
   TRANSACTION_DATE     DATE                 not null,
   TRANSACTON_AMOUNT    NUMBER(10,2)         not null,
   "TO"                 NUMBER(10)           not null,
   constraint PK_TRANSACTION_HISTORY primary key (TRANSACTION_ID)
);

/*==============================================================*/
/* Index: TRANSACTION_FK                                        */
/*==============================================================*/
create index TRANSACTION_FK on TRANSACTION_HISTORY (
   ACCT_ID ASC
);

alter table TRANSACTION_HISTORY
   add constraint FK_TRANSACT_TRANSACTI_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

/*==============================================================*/
/* Milestone 2 changes                                          */
/*==============================================================*/

CREATE OR REPLACE VIEW remaining_loan_view AS 
	SELECT acct_id as acct_id, interest_rate as intr_rate, remaining_loan_term as remain_term FROM LOAN;
    
CREATE OR REPLACE VIEW all_emps AS
    SELECT EMPLOYEE_ID as emp_id, FIRST_NAME as f_name, LAST_NAME as l_name, SOCIAL_SECURITY as ssn, POSITION as position, HIRE_DATE as hire_date, SALARY as sal FROM EMPLOYEE ORDER BY SALARY DESC;

CREATE OR REPLACE VIEW emps_with_accts AS 
    SELECT DISTINCT EMPLOYEE_ID as emp_id, FIRST_NAME as f_name, LAST_NAME as l_name
    FROM EMPLOYEE WHERE 
        EMPLOYEE_ID IN(SELECT EMPLOYEE_ID FROM ACCOUNT 
                       WHERE EMPLOYEE_ID IS NOT NULL); 

CREATE OR REPLACE VIEW emps_with_above_avg_sal AS 
    SELECT EMPLOYEE_ID as emp_id, SALARY as sal, FIRST_NAME as f_name, LAST_NAME as l_name
    FROM EMPLOYEE WHERE 
        SALARY >= (SELECT AVG(SALARY) FROM EMPLOYEE); 

CREATE OR REPLACE VIEW emps_with_below_avg_sal AS 
    SELECT EMPLOYEE_ID as emp_id, SALARY as sal, FIRST_NAME as f_name, LAST_NAME as l_name
    FROM EMPLOYEE WHERE 
        SALARY <= (SELECT AVG(SALARY) FROM EMPLOYEE); 

DROP SEQUENCE emp_seq; 

CREATE SEQUENCE emp_seq START WITH 1000000000;  

DROP SEQUENCE acct_seq; 

CREATE SEQUENCE acct_seq START WITH 1000000000; 

DROP SEQUENCE trans_seq;

CREATE SEQUENCE trans_seq START WITH 1000000000;

CREATE OR REPLACE TRIGGER new_checking
BEFORE INSERT ON CHECKING
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
    
    SELECT v_today
    INTO :new.OPEN_DATE
    FROM dual;
    
    INSERT INTO ACCOUNT(ACCT_ID, EMPLOYEE_ID, SSN, BALANCE, OPEN_DATE) VALUES
    (v_next, :new.EMPLOYEE_ID, :new.SSN, :new.BALANCE, v_today);
END;
/

CREATE OR REPLACE TRIGGER new_credit
BEFORE INSERT ON CREDIT_CARD
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
    
    SELECT v_today
    INTO :new.OPEN_DATE
    FROM dual;
    
    INSERT INTO ACCOUNT(ACCT_ID, EMPLOYEE_ID, SSN, BALANCE, OPEN_DATE) VALUES
    (v_next, :new.EMPLOYEE_ID, :new.SSN, :new.BALANCE, v_today);
END;
/

CREATE OR REPLACE TRIGGER new_hire_attempt
INSTEAD OF INSERT ON all_emps
FOR EACH ROW
BEGIN
INSERT INTO EMPLOYEE(POSITION, SALARY, HIRE_DATE, FIRST_NAME, LAST_NAME, SOCIAL_SECURITY)
VALUES(:new.position, :new.sal, :new.hire_date, :new.f_name, :new.l_name, :new.ssn);
END;
/

CREATE OR REPLACE TRIGGER new_hire 
BEFORE INSERT ON EMPLOYEE 
FOR EACH ROW 
BEGIN 
    SELECT emp_seq.NEXTVAL 
    INTO :new.EMPLOYEE_ID 
    FROM dual;
END; 
/

CREATE OR REPLACE TRIGGER fire_attempt
INSTEAD OF DELETE ON all_emps
FOR EACH ROW
BEGIN
DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID = :old.emp_id;
END;
/

CREATE OR REPLACE TRIGGER new_loan 
BEFORE INSERT ON LOAN
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
    
    SELECT v_today
    INTO :new.OPEN_DATE
    FROM dual;
    
    INSERT INTO ACCOUNT(ACCT_ID, EMPLOYEE_ID, SSN, BALANCE, OPEN_DATE) VALUES
    (v_next, :new.EMPLOYEE_ID, :new.SSN, :new.BALANCE, v_today);
END;
/

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
    
    SELECT v_today
    INTO :new.OPEN_DATE
    FROM dual;
    
    INSERT INTO ACCOUNT(ACCT_ID, EMPLOYEE_ID, SSN, BALANCE, OPEN_DATE) VALUES
    (v_next, :new.EMPLOYEE_ID, :new.SSN, :new.BALANCE, v_today);
END;
/

CREATE OR REPLACE TRIGGER new_trans
BEFORE INSERT ON TRANSACTION_HISTORY
FOR EACH ROW
BEGIN
    SELECT trans_seq.NEXTVAL
    INTO :new.TRANSACTION_ID
    FROM dual;
END;
/

CREATE OR REPLACE PROCEDURE calc_salary_exp(yearly out NUMBER, monthly out NUMBER) IS
    CURSOR emp_cur IS SELECT SALARY from EMPLOYEE;
    v_temp number(10,2);
    v_benefits number(10,2);
BEGIN
    yearly := 0;
    OPEN emp_cur;
LOOP
    FETCH emp_cur INTO v_temp;
    EXIT WHEN emp_cur%NOTFOUND;
    IF v_temp < 41629.00 THEN
        v_benefits := 1000.00;
        v_temp := v_temp + (v_temp * .06) + v_benefits;
    ELSIF v_temp < 52612.00 THEN
        v_benefits := 2000.00;
        v_temp := v_temp + (v_temp * .08) + v_benefits;
    ELSIF v_temp < 268750.00 THEN
        v_benefits := 3000.00;
        v_temp := v_temp + (v_temp * .093) + v_benefits;
    ELSE
        v_benefits := 4000.00;
        v_temp := v_temp + (v_temp * .103) + v_benefits;
    END IF;
    yearly := yearly + v_temp;
    dbms_output.put_line(yearly);
END LOOP;
monthly := yearly/12;
CLOSE emp_cur;
END calc_salary_exp;
/

CREATE OR REPLACE PROCEDURE hire (position in varchar2, salary in number, hiredate in date, first in varchar2, last in varchar2, social in char) IS
BEGIN
    INSERT INTO all_emps(position, sal, hire_date, f_name, l_name, ssn) VALUES (position, salary, hiredate, first, last, social);
    COMMIT;
END hire;
/

CREATE OR REPLACE PROCEDURE fire (emp in number, test out number) IS
BEGIN
    SELECT count(emp_id) into test from all_emps where emp_id = emp;
    IF  test > 0 THEN
        DELETE FROM all_emps WHERE emp_id = emp;
        COMMIT;
        test := 1;
    ELSE
        test := 0;
    END IF;
END fire;
/

CREATE OR REPLACE PROCEDURE emp_net_sal(emp_id in NUMBER, netyearly out NUMBER, netmonthly out NUMBER) IS
    v_benefits number(10,2);
BEGIN
    netyearly := 0;
    netmonthly := 0;
    
    SELECT SALARY INTO netyearly FROM EMPLOYEE WHERE EMPLOYEE_ID = emp_id;
    
    IF netyearly < 41629.00 THEN
        v_benefits :=1000.00;
        netyearly := netyearly - (netyearly * .06) + v_benefits;
    ELSIF netyearly < 52612.00 THEN
        v_benefits := 2000.00;
        netyearly := netyearly - (netyearly * .08) + v_benefits;
    ELSIF netyearly < 268750.00 THEN
        v_benefits := 3000.00;
        netyearly := netyearly - (netyearly * .093) + v_benefits;
    ELSE
        v_benefits := 4000.00;
        netyearly := netyearly - (netyearly * .103) + v_benefits;
    END IF;
    netmonthly := netyearly/12;
END emp_net_sal;
/

