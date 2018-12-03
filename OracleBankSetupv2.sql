/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     11/30/2018 9:28:18 AM                        */
/*==============================================================*/


/*alter table ACCOUNT
   drop constraint FK_ACCOUNT_EMPLOYEE__EMPLOYEE;*/

alter table ACCOUNT
   drop constraint FK_ACCOUNT_HAS_ACCOU_CUSTOMER;

alter table CHECKING
   drop constraint FK_CHECKING_IS_CHECKI_ACCOUNT;

alter table CREDIT_CARD
   drop constraint FK_CREDIT_C_IS_CREDIT_ACCOUNT;

alter table LOAN
   drop constraint FK_LOAN_IS_LOAN_ACCOUNT;

alter table SAVINGS
   drop constraint FK_SAVINGS_IS_SAVING_ACCOUNT;

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
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2) not null CHECK(BALANCE >= 0),
   OPEN_DATE            DATE                 not null,
   constraint PK_ACCOUNT primary key (ACCT_ID)
);

/*==============================================================*/
/* Table: CHECKING                                              */
/*==============================================================*/
create table CHECKING 
(
   ACCT_ID              NUMBER(10)             not null,
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
   ACCT_ID              NUMBER(10)             not null,
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
/* Table: LOAN                                                  */
/*==============================================================*/
create table LOAN 
(
   ACCT_ID              NUMBER(10)             not null,
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
   ACCT_ID              NUMBER(10)             not null,
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
   TRANSACTION_ID       NUMBER(10)             not null,
   ACCT_ID              NUMBER(10)             not null,
   TRANSACTION_DATE     DATE                 not null,
   TRANSACTON_AMOUNT    NUMBER(10,2)         not null,
   "TO"                 NUMBER(10)             not null,
   constraint PK_TRANSACTION_HISTORY primary key (TRANSACTION_ID)
);

/*==============================================================*/
/* Index: TRANSACTION_FK                                        */
/*==============================================================*/
create index TRANSACTION_FK on TRANSACTION_HISTORY (
   ACCT_ID ASC
);

/*alter table ACCOUNT
   add constraint FK_ACCOUNT_EMPLOYEE__EMPLOYEE foreign key (EMPLOYEE_ID)
      references EMPLOYEE (EMPLOYEE_ID) on DELETE SET NULL;*/

alter table ACCOUNT
   add constraint FK_ACCOUNT_HAS_ACCOU_CUSTOMER foreign key (SSN)
      references CUSTOMER (SSN);

alter table CHECKING
   add constraint FK_CHECKING_IS_CHECKI_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

alter table CREDIT_CARD
   add constraint FK_CREDIT_C_IS_CREDIT_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

alter table LOAN
   add constraint FK_LOAN_IS_LOAN_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

alter table SAVINGS
   add constraint FK_SAVINGS_IS_SAVING_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

alter table TRANSACTION_HISTORY
   add constraint FK_TRANSACT_TRANSACTI_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

/*==============================================================*/
/* Milestone 2 changes                                          */
/*==============================================================*/
CREATE OR REPLACE VIEW remaining_loan_view AS 
	SELECT acct_id as acct_id, interest_rate as intr_rate, remaining_loan_term as remain_term FROM LOAN;

CREATE OR REPLACE VIEW emps_with_accts AS 
    SELECT DISTINCT EMPLOYEE_ID as emp_id, FIRST_NAME as f_name, LAST_NAME as l_name
    FROM EMPLOYEE WHERE 
        EMPLOYEE_ID IN(SELECT EMPLOYEE_ID FROM ACCOUNT 
                       WHERE EMPLOYEE_ID IS NOT NULL); 

CREATE OR REPLACE VIEW emps_with_above_avg_sal AS 
    SELECT EMPLOYEE_ID as emp_id, SALARY as sal, FIRST_NAME as f_name, LAST_NAME as l_name
    FROM EMPLOYEE WHERE 
        SALARY > (SELECT AVG(SALARY) FROM EMPLOYEE); 

CREATE OR REPLACE VIEW emps_with_below_avg_sal AS 
    SELECT EMPLOYEE_ID as emp_id, SALARY as sal, FIRST_NAME as f_name, LAST_NAME as l_name
    FROM EMPLOYEE WHERE 
        SALARY < (SELECT AVG(SALARY) FROM EMPLOYEE); 

DROP SEQUENCE emp_seq; 

CREATE SEQUENCE emp_seq START WITH 1000000000;  

DROP SEQUENCE acct_seq; 

CREATE SEQUENCE acct_seq START WITH 1000000000; 

DROP SEQUENCE trans_seq;

CREATE SEQUENCE trans_seq START WITH 1000000000;



