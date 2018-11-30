package frontend;

import javax.swing.*;
import javax.swing.border.*;

import backend.EmployeeFunctions;

import java.awt.*;
import java.awt.event.*;

public class EmployeePanel extends JPanel {
	
	public EmployeePanel() {
		super();
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridLayout(7,1,10,10));
		setBorder(new EmptyBorder(10,10,10,10));
		
		JButton empsWAccts = new JButton("View Employees With Accounts");
		
		JButton empsWAbvAvgSal = new JButton("View Employees With Above Avg Salary");
		
		JButton empsWBelowAvgSal = new JButton("View Employees With Below Avg Salary");
		
		JButton calcSalExp = new JButton("Calculate Salary Expense");
		
		JButton hire = new JButton("Hire Employee");
		hire.addActionListener((x) -> {
			JFrame jf = (JFrame) SwingUtilities.getWindowAncestor(this);
			JTextField firstname = new JTextField();
			JTextField lastname = new JTextField();
			JTextField ssn = new JTextField();
			JTextField conssn = new JTextField();
			JTextField salary = new JTextField();
			JTextField job = new JTextField();
			JTextField startDate = new JTextField();
			
			Object[] message = {
				"First Name: ", firstname,
				"Last Name: ", lastname,
				"SSN: ", ssn,
				"Confirm SSN: ", conssn,
				"Employee Salary: ", salary,
				"Employee Job: ", job,
				"Start Date: ", startDate
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Hire Employee", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				EmployeeFunctions.hire(jf, firstname.getText(),lastname.getText(),ssn.getText(),conssn.getText(),salary.getText(),job.getText(),startDate.getText());
			}
		});
		
		JButton fire = new JButton("Fire Employee");
		fire.addActionListener((x) -> {
			JFrame jf = (JFrame) SwingUtilities.getWindowAncestor(this);
			JTextField empid = new JTextField();
			JTextField conempid = new JTextField();
			
			Object[] message = {
				"Employee ID: ", empid,
				"Confirm Employee ID: ", conempid
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Fire Employee", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				EmployeeFunctions.fire(jf, empid.getText(), conempid.getText());
			}
			
		});
		
		JButton main = new JButton("Main Menu");
		main.addActionListener((x) -> {
			JFrame jf = (JFrame) SwingUtilities.getWindowAncestor(this);
			Component[] comps=jf.getContentPane().getComponents();
			comps[0].setVisible(true);
			setVisible(false);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height = (int)(screenSize.getHeight()/4);
			int width = (int)(screenSize.getWidth()/4);
			jf.setSize(width, height);
		});
		
		add(empsWAccts);
		add(empsWAbvAvgSal);
		add(empsWBelowAvgSal);
		add(calcSalExp);
		add(hire);
		add(fire);
		add(main);
		
		for(int i = 0; i < getComponentCount(); i++){
			
			if(getComponents()[i] instanceof JButton){
				getComponents()[i].setFont(new Font("Arial", Font.PLAIN, 30));
			}
		}
	}
}
