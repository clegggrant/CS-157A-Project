				Bank Management System Setup
1. Makes sure you have Java 8 or newer installed

2. You will need to have Oracle 11g installed. You can do so from this link.

	a. IMPORTANT: Installation asks you to make a password. Remember that password. You 	   will need it in the next step to create the Client user.

3. Make sure the database is running. You can run the Start Database file was that created    during Installation.

4. Once you have 11g downloaded and installed, run the sql terminal and execute the       following steps

	a. CONNECT SYS/password AS SYSDBA

		Password is the password you made during installation of Oracle 11g.

	b. If using the sqlplus terminal
		
                Username: SYS AS SYSDBA
		Password: The password you created during installation

5. Run the OracleBankSetup.sql followed by the all_sample_data.sql scripts. They are in the SQLSourceCode folder of the submission. OracleBankSetup.sql will create the Client user needed by the program, grant Client the appropriate permissions, connect as the Client user and generate the database. The all_sample_data.sql script will populate the tables created by the first script.

	a. If on windows 

	   @”c:\my\file\path\scriptNameHere.sql” 

	b. If on linux 

	   START /my/file/path/scriptNameHere.sql

6. In the terminal enter COMMIT; 

7.You are ready to run the Bank.jar file.
