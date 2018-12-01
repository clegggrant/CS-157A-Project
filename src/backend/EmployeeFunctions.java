package backend;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import oracle.jdbc.OracleTypes;

public class EmployeeFunctions {
	
	
	public static boolean hire(JFrame jf, String firstname, String lastname, String ssn, String conssn, String salary, String job, String startdate) {
		if(firstname.length() > 15 || lastname.length() > 15) {
			JOptionPane.showMessageDialog(jf, "First and Last names must both be under 15 characers!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!ssn.equals(conssn)) {
			JOptionPane.showMessageDialog(jf, "SSNs do not match!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String pattern = "\\d{3}-\\d{2}-\\d{4}";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(ssn);
		if(!m.find()) {
			JOptionPane.showMessageDialog(jf, "Use XXX-XX-XXXX format for SSN!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String pattern2 = "^([0-2][0-9]||3[0-1])-(JAN||FEB||MAR||APR||MAY||JUN||JUL||AUG||SEP||OCT||NOV||DEC)-([0-9][0-9])?[0-9][0-9]$";
		Pattern p2 = Pattern.compile(pattern2);
		Matcher m2 = p2.matcher(startdate);
		if(!m2.find()) {
			JOptionPane.showMessageDialog(jf, "Please use DD-MON-YYYY format for start date!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		boolean status = false;
		try {
			//defining database driver to use
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//getting connection from the mysql database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			
			if(con != null) {
				System.out.println("Connection established!");
			}
			else {
				 System.out.println("Connection Failed! Check output console");
			}

			//prepared statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from users where username = name and password = pass
			PreparedStatement oPrStmt = con
					.prepareStatement("INSERT INTO EMPLOYEE "
							+ "(position, salary, hire_date, first_name, last_name, social_security)"
							+ " VALUES(?,?,TO_DATE(?,'DD-MON-YYYY'),?,?,?)");					
			
			//setting ? variables in the above statement
			// parameter index start from 1
			oPrStmt.setString(1, job);
			oPrStmt.setString(2, salary);
			oPrStmt.setString(3, startdate);
			oPrStmt.setString(4, firstname);
			oPrStmt.setString(5, lastname);
			oPrStmt.setString(6, ssn);
			
			int nInsertedRecords = oPrStmt.executeUpdate(); // executing the query and getting the updated/inserted row counts from databse
			
			if(nInsertedRecords>0){ // check that the data is inserted successfully or not
				status = true;
				
				//Call front end function to notify that new hire has been added
				System.out.println("New hire has been successfully inserted");
			}
	
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
	
		return status;
		
	}
	
	public static boolean fire(JFrame jf, String empid, String conempid) {
		if(!empid.equals(conempid)) {
			JOptionPane.showMessageDialog(jf, "Employee IDs do not match!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String pattern = "\\d{10}";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(empid);
		if(empid.length() != 10 || !m.find()) {
			JOptionPane.showMessageDialog(jf, "Employee ID should be 10 digit number!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		boolean status = false;
		try {
			//defining database driver to use
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//getting connection from the mysql database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			
			if(con != null) {
				System.out.println("Connection established!");
			}
			else {
				 System.out.println("Connection Failed! Check output console");
			}

			//prepared statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from users where username = name and password = pass
			PreparedStatement oPrStmt = con
					.prepareStatement("DELETE FROM EMPLOYEE WHERE employee_id=?");					
			
			//setting ? variables in the above statement
			// parameter index start from 1
			oPrStmt.setInt(1, Integer.parseInt(empid));

			int nUpdatedRecords = oPrStmt.executeUpdate(); // executing the query and getting the updated/inserted row counts from databse
			
			if(nUpdatedRecords>0){ // check that the data is deleted successfully or not
				status = true;
				
				//call front end to notify employee has been deleted
				System.out.println("Employee " +  empid + " has been successfully removed");
				
			}
			else{
				//call front end to notify unable to delete 
				System.out.println("Unsuccessful Deletion, wrong ID?");
			}
	
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
	
		return status;
	}
	
	public static boolean calcSalExpense() {
		boolean status = false;
		try {
			//defining database driver to use
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//getting connection from the mysql database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			
			if(con != null) {
				System.out.println("Connection established!");
			}
			else {
				 System.out.println("Connection Failed! Check output console");
			}

			//prepared statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from users where username = name and password = pass
			CallableStatement cStmt = con
					.prepareCall("{CALL calc_salary_exp(?,?)}");				
		
		
			cStmt.registerOutParameter(1, OracleTypes.NUMBER);
			cStmt.registerOutParameter(2, OracleTypes.NUMBER);
			
			cStmt.execute();
		
			status = true;
			int yearly = cStmt.getInt(1);
			int monthly = cStmt.getInt(2);
				
			//call frontend and send this information as parameter
			System.out.println("YEARLY: " + yearly + " MONTHLY: " + monthly);
				
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
	
		return status;
	}
	
	public static boolean empsWBelowAvgSal() {
		boolean status = false;
		try {
			//defining database driver to use
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//getting connection from the mysql database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			
			if(con != null) {
				System.out.println("Connection established!");
			}
			else {
				 System.out.println("Connection Failed! Check output console");
			}

			//prepared statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from users where username = name and password = pass
			PreparedStatement oPrStmt = con
					.prepareStatement("Select * FROM emps_with_below_avg_sal");					
		
			ResultSet rs = oPrStmt.executeQuery(); // executing the query and getting the resultset from databse
			
			//rs.next() shows that the resultset contains nect value or not
			// for retrieving multiple results, you can use while(rs.next)
			
			while(rs.next()) { //checking while the resultset has any value? 
				status = true;
				System.out.println("Returned Results, Getting data");
			
				int empID = rs.getInt("emp_id");
				int sal = rs.getInt("sal");
				
				//call front end, passing the data of empID and salary
				System.out.println("You've received data: " + empID + " " + sal);
				
			}//end rs.next while statement
	
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
	
		return status;
	}
	
	public static boolean empsWAboveAvgSal() {
		boolean status = false;
		try {
			//defining database driver to use
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//getting connection from the mysql database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			
			if(con != null) {
				System.out.println("Connection established!");
			}
			else {
				 System.out.println("Connection Failed! Check output console");
			}

			//prepared statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from users where username = name and password = pass
			PreparedStatement oPrStmt = con
					.prepareStatement("Select * FROM emps_with_above_avg_sal");					
		
			ResultSet rs = oPrStmt.executeQuery(); // executing the query and getting the resultset from databse
			
			//rs.next() shows that the resultset contains nect value or not
			// for retrieving multiple results, you can use while(rs.next)
			
			while(rs.next()) { //checking while the resultset has any value? 
				status = true;
				System.out.println("Returned Results, Getting data");
			
				int empID = rs.getInt("emp_id");
				int sal = rs.getInt("sal");
				
				//call front end, passing the data of empID and sal
				System.out.println("You've received data: " + empID + " " + sal);
				
			}//end rs.next while statement
	
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
	
		return status;
	}
	
	public static boolean empsWAccts() {
		boolean status = false;
		try {
			//defining database driver to use
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//getting connection from the mysql database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			
			if(con != null) {
				System.out.println("Connection established!");
			}
			else {
				 System.out.println("Connection Failed! Check output console");
			}

			//prepared statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from users where username = name and password = pass
			PreparedStatement oPrStmt = con
					.prepareStatement("Select * From emps_with_accts");					
		
			ResultSet rs = oPrStmt.executeQuery(); // executing the query and getting the resultset from databse
			
			//rs.next() shows that the resultset contains nect value or not
			// for retrieving multiple results, you can use while(rs.next)
			
			while(rs.next()) { //checking while the resultset has any value? 
				status = true;
				System.out.println("Returned Results, Getting data");
			
				int empID = rs.getInt("emp_id");
				
				//call front end, passing the data of empID
				System.out.println("You've received data: " + empID);
				
			}//end rs.next while statement
	
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
	
		return status;
	}
}
