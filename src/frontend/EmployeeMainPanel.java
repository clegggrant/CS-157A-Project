package frontend;

import java.awt.CardLayout;

import java.awt.Dimension;
import java.awt.Toolkit;

/*
 * 
 * @author: Yu Jun Zhao
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.ViewportUI;

import backend.EmployeeFunctions;

// The employee Menu

public class EmployeeMainPanel{
	// Contains ALL the panels
	private JPanel cards;
	
	// This is the panel that connects to other panels.
	private JPanel employeePanel; 
	
	public static final String EMPPANEL = "EMPLOYEE OVERVIEW";
	public static final String VIEWEMPACC = "View Employees with Customer Accounts";
	public static final String VIEWEMPABOVE = "View Employees with Above Avg Salary";
	public static final String VIEWEMPBELOW = "View Employees with Below Avg Salary";
	public static final String SALEXPENSE = "Calculate Salary Expense";
	public static final String HIREEMP = "Hire Employees";
	public static final String FIREEMP = "Fire Employees";
	public static final String MAINMENU = "Main Menu";

	
	public EmployeeMainPanel(){
		// Setup the Frame
		
	    createEmployeePanel();
		loadPanelsToCards();	    

		CardLayout c1 = (CardLayout)cards.getLayout();
		c1.show(cards,EMPPANEL);
	}
	private void createEmployeePanel(){
		employeePanel = new JPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
	    employeePanel.setPreferredSize(new Dimension(width/3,height/3));
		
		JButton viewEmpAcc = new JButton(VIEWEMPACC);
		viewEmpAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//call whatever frame or perform whatever action
				System.out.println("Viewing Employees with Accounts");
				boolean result = EmployeeFunctions.get_employees_with_accounts();

				CardLayout c1 = (CardLayout)cards.getLayout();
				c1.show(cards,VIEWEMPACC);
			}
		});
		
		JButton viewEmpAbove = new JButton(VIEWEMPABOVE);
		viewEmpAbove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Viewing Employees with Below Avg Salary");
				
			}
		});
		
		JButton viewEmpBelow = new JButton(VIEWEMPBELOW);
		viewEmpBelow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Viewing Employees with Below Avg Salary");
				
			}
		});
		
		JButton salExpense = new JButton(SALEXPENSE);
		salExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Calculating Salary Expense");
				
			}
		});

		JButton hireEmp = new JButton(HIREEMP);
		hireEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Hiring an Employee");
				
			}
		});
		
		JButton fireEmp = new JButton(FIREEMP);
		fireEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Firing an Employee");
			}
		});
		
		
		
		JButton mainMenu = new JButton(MAINMENU);
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Calling the Main Menu");
				JFrame menu = new Menu();
			}
		});
		
		
		employeePanel.add(viewEmpAcc);
		employeePanel.add(viewEmpAbove);
		employeePanel.add(viewEmpBelow);
		employeePanel.add(salExpense);
		employeePanel.add(hireEmp);
		employeePanel.add(fireEmp);
		employeePanel.add(mainMenu);

		// CHANGE THE FONTS
		for(int i = 0; i < employeePanel.getComponentCount(); i++){
			
			if(employeePanel.getComponents()[i] instanceof JButton){
				employeePanel.getComponents()[i].setFont(Menu.FONT);
			}
		}
	}
	
	
	private void loadPanelsToCards(){
		this.cards = new JPanel(new CardLayout());
		this.cards.add(employeePanel, EMPPANEL);
		String[] view_emp_col = {"Employee Id", "Checking Id", "Saving Id", "Credit Id"};
		this.cards.add(new TableInfoPanel("View Employees", view_emp_col), VIEWEMPACC);
	}
	
	public JPanel getCards(){return this.cards;}
}
