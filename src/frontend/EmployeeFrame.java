package frontend;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EmployeeFrame extends JFrame{

	public EmployeeFrame() {
		
		setSize(300,230);
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		
		JButton viewEmpAcc = new JButton("View Employees With Accounts");
		buttonPanel.add(viewEmpAcc);
		viewEmpAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call whatever frame or perform whatever action
				System.out.println("Viewing Employees with Accounts");
				
			}
		});
		
		JButton viewEmpAbove = new JButton("View Employees With Above Avg Salary");
		buttonPanel.add(viewEmpAbove);
		viewEmpAbove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call whatever frame or perform whatever action
				System.out.println("Viewing Employees with Below Avg Salary");
				
			}
		});
		
		JButton viewEmpBelow = new JButton("View Employees With Below Avg Salary");
		buttonPanel.add(viewEmpBelow);
		viewEmpBelow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call whatever frame or perform whatever action
				System.out.println("Viewing Employees with Below Avg Salary");
				
			}
		});
		
		JButton salExpense = new JButton("Calculate Salary Expense");
		buttonPanel.add(salExpense);
		salExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call whatever frame or perform whatever action
				System.out.println("Calculating Salary Expense");
				
			}
		});

		JButton hireEmp = new JButton("Hire Employees");
		buttonPanel.add(hireEmp);
		hireEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call whatever frame or perform whatever action
				System.out.println("Hiring an Employee");
				
			}
		});
		
		JButton fireEmp = new JButton("Fire Employees");
		buttonPanel.add(fireEmp);
		fireEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call whatever frame or perform whatever action
				System.out.println("Firing an Employee");
			}
		});
		
		
		
		JButton mainMenu = new JButton("Main Menu");
		buttonPanel.add(mainMenu);
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call the employee management frame
				System.out.println("Calling the Main Menu");
				JFrame menu = new Menu();
			}
		});
		
		
		add(buttonPanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
