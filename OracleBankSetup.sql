/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     11/28/2018 9:20:18 PM                        */
/*==============================================================*/


alter table ACCOUNT
   drop constraint FK_ACCOUNT_HAS_ACCOU_CUSTOMER;

alter table CHECKING
   drop constraint FK_CHECKING_IS_CHECKI_ACCOUNT;

alter table CREDIT_CARD
   drop constraint FK_CREDIT_C_IS_CREDIT_ACCOUNT;

alter table EMPLOYEE 
   drop constraint FK_EMPLOYEE_CAN_BE_CUSTOMER;

alter table LOAN
   drop constraint FK_LOAN_IS_LOAN_ACCOUNT;

alter table SAVINGS
   drop constraint FK_SAVINGS_IS_SAVING_ACCOUNT;

alter table TRANSACTION_HISTORY
   drop constraint FK_TRANSACT_TRANSACTI_ACCOUNT;

drop index HAS_ACCOUNT_FK;

drop table ACCOUNT cascade constraints;

drop table CHECKING cascade constraints;

drop table CREDIT_CARD cascade constraints;

drop index CAN_BE_FK;

drop table CUSTOMER cascade constraints;

drop index CAN_BE2_FK;

drop table EMPLOYEE cascade constraints;

drop table LOAN cascade constraints;

drop table SAVINGS cascade constraints;

drop index TRANSACTION_FK;

drop table TRANSACTION_HISTORY cascade constraints;

/*==============================================================*/
/* Table: ACCOUNT                                               */
/*==============================================================*/
create table ACCOUNT 
(
   ACCT_ID              CHAR(10)             not null,
   SSN                  CHAR(11)             not null,
   BALANCE              NUMBER(10,2)         not null,
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
/* Table: CHECKING                                              */
/*==============================================================*/
create table CHECKING 
(
   ACCT_ID              CHAR(10)             not null,
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2)         not null,
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
   ACCT_ID              CHAR(10)             not null,
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2)         not null,
   OPEN_DATE            DATE                 not null,
   LIMIT                NUMBER(10,2)         not null,
   DUE_DATE             DATE,
   MIN__PAYMENT         NUMBER(10,2),
   LAST_STATEMENT_BALANCE NUMBER(10,2),
   LAST_PAYMENT         NUMBER(10,2),
   INTEREST_RATE        NUMBER(4,3)          not null,
   ACCTTYPE             VARCHAR2(10),
   constraint PK_CREDIT_CARD primary key (ACCT_ID)
);

/*==============================================================*/
/* Table: CUSTOMER                                              */
/*==============================================================*/
create table CUSTOMER 
(
   SSN                  CHAR(11)             not null,
   FIRST_NAME           VARCHAR2(15)         not null,
   LAST_NAME            VARCHAR2(15)         not null,
   ADDR                 VARCHAR2(30)         not null,
   JOIN_DATE            DATE                 not null,
   constraint PK_CUSTOMER primary key (SSN)
);


/*==============================================================*/
/* Table: EMPLOYEE                                              */
/*==============================================================*/
create table EMPLOYEE 
(
   EMPLOYEE_ID          CHAR(10)             not null,
   SSN                  CHAR(11)             unique,
   POSITION             VARCHAR2(20)         not null,
   SALARY               NUMBER(10,2)         not null,
   HIRE_DATE            DATE                 not null,
   constraint PK_EMPLOYEE primary key (EMPLOYEE_ID)
);

/*==============================================================*/
/* Index: CAN_BE_FK                                            */
/*==============================================================*/
create index CAN_BE_FK on EMPLOYEE (
   SSN ASC
);

/*==============================================================*/
/* Table: LOAN                                                  */
/*==============================================================*/
create table LOAN 
(
   ACCT_ID              CHAR(10)             not null,
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2)         not null,
   OPEN_DATE            DATE                 not null,
   INTEREST_RATE        NUMBER(4,3)          not null,
   TOTAL_LOAN           NUMBER(10,2)         not null,
   MONTHLY_PAYMENT      NUMBER(10,2)         not null,
   REMAINING_LOAN_TERM  INTEGER              not null,
   LOAN_TERM            INTEGER              not null,
   ACCTTYPE             VARCHAR2(10),
   constraint PK_LOAN primary key (ACCT_ID)
);

/*==============================================================*/
/* Table: SAVINGS                                               */
/*==============================================================*/
create table SAVINGS 
(
   ACCT_ID              CHAR(10)             not null,
   SSN                  CHAR(11),
   BALANCE              NUMBER(10,2)         not null,
   OPEN_DATE            DATE                 not null,
   TRANSFER_LIMIT       INTEGER              not null,
   MINIMUM_BALANCE      NUMBER(10,2)         not null,
   INTEREST_RATE        NUMBER(4,3)          not null,
   ACCTTYPE             VARCHAR2(10),
   constraint PK_SAVINGS primary key (ACCT_ID)
);

/*==============================================================*/
/* Table: TRANSACTION_HISTORY                                   */
/*==============================================================*/
create table TRANSACTION_HISTORY 
(
   TRANSACTION_ID       CHAR(10)             not null,
   ACCT_ID              CHAR(10)             not null,
   TRANSACTION_DATE     DATE                 not null,
   TRANSACTON_AMOUNT    NUMBER(10,2)         not null,
   "TO"                 CHAR(10)             not null,
   "FROM"               CHAR(10)             not null,
   constraint PK_TRANSACTION_HISTORY primary key (TRANSACTION_ID)
);

/*==============================================================*/
/* Index: TRANSACTION_FK                                        */
/*==============================================================*/
create index TRANSACTION_FK on TRANSACTION_HISTORY (
   ACCT_ID ASC
);

alter table ACCOUNT
   add constraint FK_ACCOUNT_HAS_ACCOU_CUSTOMER foreign key (SSN)
      references CUSTOMER (SSN);

alter table CHECKING
   add constraint FK_CHECKING_IS_CHECKI_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

alter table CREDIT_CARD
   add constraint FK_CREDIT_C_IS_CREDIT_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

alter table EMPLOYEE
   add constraint FK_EMPLOYEE_CAN_BE_CUSTOMER foreign key (SSN)
      references CUSTOMER (SSN) on delete set null;

alter table LOAN
   add constraint FK_LOAN_IS_LOAN_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

alter table SAVINGS
   add constraint FK_SAVINGS_IS_SAVING_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

alter table TRANSACTION_HISTORY
   add constraint FK_TRANSACT_TRANSACTI_ACCOUNT foreign key (ACCT_ID)
      references ACCOUNT (ACCT_ID);

