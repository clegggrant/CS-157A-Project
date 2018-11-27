package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeFunctions {
	
	public static boolean get_employees_with_accounts() {
		boolean status = false;
		try {
			//defining database driver to use
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//getting connection from the mysql database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","admin","admin");
			
			if(con != null) {
				System.out.println("Connection established!");
				status = true;
			}
			else {
				 System.out.println("Connection Failed! Check output console");
			}

			//prepared statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from users where username = name and password = pass
			PreparedStatement oPrStmt = con
					.prepareStatement("SELECT * FROM EMPLOYEE");
//					.prepareStatement("SELECT * FROM `user_accounts` WHERE email=? AND password=?");// ? represents some parameter to include
					
			
			//setting ? variables in the above statement
//			oPrStmt.setString(1, thisUser.email);// parameter index start from 1
//			oPrStmt.setString(2, thisUser.password);
			ResultSet rs = oPrStmt.executeQuery(); // executing the query and getting the resultset from databse
			
			//rs.next() shows that the resultset contains nect value or not
			// for retrieving multiple results, you can use while(rs.next)
			System.out.println("Executed successfully");
			
			while(rs.next()) { //checking while the resultset has any value? 
				status = true;
				System.out.println("Getting data");
			
				System.out.println("ID: " + rs.getString("employee_id") + 
						", SSN: " + rs.getString("SSN") + "\n");
				
				
			}//end rs.next while statement
	
		} catch (Exception e) {
			System.out.println(e);
		}
	
		return status;
	}

}
