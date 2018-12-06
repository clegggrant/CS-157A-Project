package common_util;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;



/*
 * A class that keeps all the static final variable
 * for the front end
 * 
 * 
 */

public class StaticVar {
	// Windows Dimension
	public static final Dimension WINDIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	
	// FONT
	public static final Font MENUFONT = new Font("Arial", Font.PLAIN, 30);
	public static final Font TABLEFONT = new Font("Arial", Font.PLAIN, 20);
	public static final Font SALARYFONT = new Font("Arial", Font.PLAIN, 25);

	
	// STATIC FINAL STRINGS ATTRIBUTES
	public static final String FIRST_NAME = "f_name";
	public static final String LAST_NAME = "l_name";
	public static final String EMPLOYEEE_ID = "emp_id";
	public static final String SALARY = "sal";
	public static final String SSN = "ssn";
	public static final String POS = "position";
	public static final String HDATE = "hire_date";

	
	// for the cardPanel
	public static final String MENU = "MENU";
	public static final String EMPANEL = "EMPLOYEE PANEL";
	
	// for hire and fire dates
	public static final LocalDate STARTDATE = LocalDate.of(2000, Month.JANUARY, 1);
	public static final LocalDate ENDDATE = LocalDate.of(2050, Month.DECEMBER, 31);
	public static final LocalDate TODAY = LocalDate.now(ZoneId.of("America/Los_Angeles"));
	public static final long NUMOFYEARSBET = ChronoUnit.YEARS.between(STARTDATE, ENDDATE);
	public static final String[] S_MONTH = {"JAN","FEB","MAR", 
											"APR","MAY","JUN",
											"JUL","AUG","SEP",
											"OCT","NOV","DEC"};
	
	// Query for employee table selection
	public static final String VIEWALL = "Select * FROM all_emps";
	public static final String VIEWACC = "SELECT * FROM emps_with_accts";
	public static final String ABOVEAVG = "Select * FROM emps_with_above_avg_sal";
	public static final String BELOWAVG = "Select * FROM emps_with_below_avg_sal";
	
}








