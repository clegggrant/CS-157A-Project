package frontend;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EmployeeFrame extends JFrame{

	public EmployeeFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
		this.setSize(width/3,height/3);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		

		JButton viewEmpAcc = new JButton("View Employees With Accounts");
		viewEmpAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call whatever frame or perform whatever action
				System.out.println("Viewing Employees with Accounts");
				
			}
		});
		
		JButton viewEmpAbove = new JButton("View Employees With Above Avg Salary");
		viewEmpAbove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.out.println("Viewing Employees with Below Avg Salary");
				
			}
		});
		
		JButton viewEmpBelow = new JButton("View Employees With Below Avg Salary");
		viewEmpBelow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				System.out.println("Viewing Employees with Below Avg Salary");
				
			}
		});
		
		JButton salExpense = new JButton("Calculate Salary Expense");
		salExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				System.out.println("Calculating Salary Expense");
				
			}
		});

		JButton hireEmp = new JButton("Hire Employees");
		hireEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				System.out.println("Hiring an Employee");
				
			}
		});
		
		JButton fireEmp = new JButton("Fire Employees");
		fireEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				System.out.println("Firing an Employee");
			}
		});
		
		
		
		JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				System.out.println("Calling the Main Menu");
				JFrame menu = new Menu();
			}
		});
		
		
		buttonPanel.add(viewEmpAcc);
		buttonPanel.add(viewEmpAbove);
		buttonPanel.add(viewEmpBelow);
		buttonPanel.add(salExpense);
		buttonPanel.add(hireEmp);
		buttonPanel.add(fireEmp);
		buttonPanel.add(mainMenu);

		// CHANGE THE FONTS
		for(int i = 0; i < buttonPanel.getComponentCount(); i++){
			
			if(buttonPanel.getComponents()[i] instanceof JButton){
				buttonPanel.getComponents()[i].setFont(Menu.FONT);
			}
		}
		
		this.add(buttonPanel);
		this.setVisible(true);

	}
}
