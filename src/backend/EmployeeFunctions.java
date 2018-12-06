package backend;

import javax.swing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

import oracle.jdbc.OracleTypes;

public class EmployeeFunctions {

	
	
	public static Data getNetSalary(JFrame jf, String empid, String conempid) {
		if(!empid.equals(conempid)) {
			JOptionPane.showMessageDialog(jf, "Employee IDs do not match!", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		String pattern = "\\d{10}";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(empid);
		if(empid.length() != 10 || !m.find()) {
			JOptionPane.showMessageDialog(jf, "Employee ID should be 10 digit number!", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Data data = new Data();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			
			CallableStatement cStmt = con
					.prepareCall("{CALL emp_net_sal(?,?,?)}");				
		
		
			cStmt.setString(1,empid);
			cStmt.registerOutParameter(2, OracleTypes.NUMBER);
			cStmt.registerOutParameter(3, OracleTypes.NUMBER);
			
			//this returns false
			cStmt.execute();
				
			data.yearly = cStmt.getDouble(2);
			data.monthly = cStmt.getDouble(3);	
		
			con.close();
			
		} catch (SQLException e) {
			
			String notice = ("Unable to find employee. Please try again.");
			JOptionPane.showMessageDialog(jf, notice);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} 
	
		return data;
	}
	public static boolean hire(JFrame jf, String firstname, String lastname, String ssn, String conssn, String salary, String job, String startdate) {
		if(firstname.length() > 15 || lastname.length() > 15 || salary.length() > 15) {
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
		
		String pattern2 = "^([1-9]||[1-2][0-9]||3[0-1])-(JAN||FEB||MAR||APR||MAY||JUN||JUL||AUG||SEP||OCT||NOV||DEC)-([0-9][0-9])?[0-9][0-9]$";

		Pattern p2 = Pattern.compile(pattern2);
		Matcher m2 = p2.matcher(startdate);
		if(!m2.find()) {
			JOptionPane.showMessageDialog(jf, "Please use DD-MON-YYYY format for start date!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		boolean status = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//getting connection from the mysql database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");

			con.setAutoCommit(false);
			CallableStatement cStmt = con
					.prepareCall("{CALL hire(?,?,?,?,?,?)}");			
			
		
			cStmt.setString(1, job);
			cStmt.setString(2, salary);
			cStmt.setString(3, startdate);
			cStmt.setString(4, firstname);
			cStmt.setString(5, lastname);
			cStmt.setString(6, ssn);
			
			cStmt.execute(); 
			con.commit();
			
			status = true;
			
			Data data = new Data();
			data.firstName.add(firstname);
			data.lastName.add(lastname);
			
			
			String notice = data.firstName.get(0) + " " + data.lastName.get(0) + " has been successfully hired";
			JOptionPane.showMessageDialog(jf, notice);
			

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
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			

			con.setAutoCommit(false);
			double test = 0;
			CallableStatement cStmt = con
					.prepareCall("{CALL fire(?,?)}");
			
		
			cStmt.setDouble(1, Integer.parseInt(empid));
			cStmt.registerOutParameter(2, OracleTypes.NUMBER);
			

			cStmt.execute();; 
			test = cStmt.getDouble(2);
			con.commit();
			if(test > 0){ 
				status = true;
				
				Data data = new Data();
				data.employeeID.add(Integer.parseInt(empid));
				
				String notice = ("Employee " +  data.employeeID.get(0) + " has been successfully removed");
				JOptionPane.showMessageDialog(jf, notice);
			}
			else{
				String notice = ("Unable to find employee. Please try again.");
				JOptionPane.showMessageDialog(jf, notice);
			}
	
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
	
		return status;
	}
	
	public static Data calcSalExpense(JFrame jf) {
		Data data = new Data();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
		
			CallableStatement cStmt = con
					.prepareCall("{CALL calc_salary_exp(?,?)}");				
		
		
			cStmt.registerOutParameter(1, OracleTypes.NUMBER);
			cStmt.registerOutParameter(2, OracleTypes.NUMBER);
			
			cStmt.execute();
		
			double yearly = cStmt.getDouble(1);
			double monthly = cStmt.getDouble(2);
			
			data.yearly = yearly;
			data.monthly = monthly;
				
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
	
		return data;
	}
	
	
	//--------------------------------------
	
	public static Data empsWBelowAvgSal(JFrame jf, String[] requestAttrOrder) {
		Data data = new Data();
		if(requestAttrOrder.length == 4){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
				
				PreparedStatement oPrStmt = con
						.prepareStatement("Select * FROM emps_with_below_avg_sal");					
			
				ResultSet rs = oPrStmt.executeQuery(); 
				
				while(rs.next()) { 
					
					String[] storedArr = new String[requestAttrOrder.length];
					storedArr[0] = getStringValue(rs, requestAttrOrder[0]);
					storedArr[1] = getStringValue(rs, requestAttrOrder[1]);
					storedArr[2] = getStringValue(rs, requestAttrOrder[2]);
					storedArr[3] = getStringValue(rs, requestAttrOrder[3]);
					data.dataValues.add(storedArr);
					
					
				}
		
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		
		}
	
		return data;
	}
	
	public static Data empsWAboveAvgSal(JFrame jf, String[] requestAttrOrder) {
		Data data = new Data();
		if(requestAttrOrder.length == 4){

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
			
				PreparedStatement oPrStmt = con
						.prepareStatement("Select * FROM emps_with_above_avg_sal");					
			
				ResultSet rs = oPrStmt.executeQuery(); 
				
				while(rs.next()) { 
					
					String[] storedArr = new String[requestAttrOrder.length];
					storedArr[0] = getStringValue(rs, requestAttrOrder[0]);
					storedArr[1] = getStringValue(rs, requestAttrOrder[1]);
					storedArr[2] = getStringValue(rs, requestAttrOrder[2]);
					storedArr[3] = getStringValue(rs, requestAttrOrder[3]);
					data.dataValues.add(storedArr);
					
				}
		
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			} 
		
		}
	
		return data;
	}
	
	public static Data empsWAccts(JFrame jf, String[] requestAttrOrder) {
		Data data = new Data();
		Connection con = null;
		if(requestAttrOrder.length == 3){
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");
				

				PreparedStatement oPrStmt = con
						.prepareStatement("SELECT * FROM emps_with_accts");					
			
				ResultSet rs = oPrStmt.executeQuery(); 

				while(rs.next()) { 
				
					String[] storedArr = new String[requestAttrOrder.length];
					storedArr[0] = getStringValue(rs, requestAttrOrder[0]);
					storedArr[1] = getStringValue(rs, requestAttrOrder[1]);
					storedArr[2] = getStringValue(rs, requestAttrOrder[2]);
					data.dataValues.add(storedArr);
					
				}
		
			
			} catch (Exception e) {
				System.out.println(e);
				
			} finally{
				try {
					if(con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	public static Data viewAllEmps(JFrame jf, String[] requestAttrOrder) {
		Data data = new Data();

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Client","password");


			PreparedStatement oPrStmt = con
						.prepareStatement("Select * FROM all_emps");					
			
				ResultSet rs = oPrStmt.executeQuery(); 
				
				while(rs.next()) { 

					String[] storedArr = new String[requestAttrOrder.length];
					storedArr[0] = getStringValue(rs, requestAttrOrder[0]);
					storedArr[1] = getStringValue(rs, requestAttrOrder[1]);
					storedArr[2] = getStringValue(rs, requestAttrOrder[2]);
					storedArr[3] = getStringValue(rs, requestAttrOrder[3]);
					storedArr[4] = getStringValue(rs, requestAttrOrder[4]);
					storedArr[5] = getStringValue(rs, requestAttrOrder[5]);
					storedArr[6] = getStringValue(rs, requestAttrOrder[6]);
					data.dataValues.add(storedArr);
					
					
				}
		
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		return data;

	}
	
	public static String getStringValue(ResultSet rs, String attr) throws SQLException{
		String str = "";
		switch(attr){
			case "emp_id":
				str = Integer.toString(rs.getInt(attr)); 
				break;
			case "f_name":
				str = rs.getString(attr);
				break;
			case "l_name":
				str = rs.getString(attr);
				break;
			case "ssn":
				str = rs.getString(attr);
				break;
			case "position":
				str = rs.getString(attr);
				break;
			case "hire_date":
				str = rs.getDate(attr).toString();
				break;
			case "sal":
				str = Double.toString(rs.getDouble(attr));
				break;
		}
		
		return str;
	}
}