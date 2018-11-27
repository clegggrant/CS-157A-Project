package frontend;

import java.awt.Dimension;
import java.awt.FlowLayout;
<<<<<<< HEAD
import java.awt.Toolkit;
=======

>>>>>>> 46e57c4c0eaf08b2f1660d13df4ae360ed1f14dd
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.EmployeeFunctions;

public class EmployeeFrame extends JFrame{
	
	// this contains all the panel necessary for scene transition
	private EmployeeMainPanel employeePanel;

<<<<<<< HEAD
=======
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
				System.out.println("Viewing Employees with Accounts and calling function");
				boolean result = EmployeeFunctions.get_employees_with_accounts();
				System.out.println("The result from the function connection: " + result);
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
>>>>>>> 46e57c4c0eaf08b2f1660d13df4ae360ed1f14dd

	public EmployeeFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
	    this.setSize(width/3,height/3);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		employeePanel = new EmployeeMainPanel();
		
		this.add(employeePanel.getCards());
		this.pack();
		this.setVisible(true);

	}
}
