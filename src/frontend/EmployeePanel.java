package frontend;

import javax.swing.*;

import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import backend.EmployeeFunctions;
import common_util.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import backend.Data;


public class EmployeePanel extends JPanel {
	
	
	private JFrame jf; // Parent Frame
	
	//JComboBoxes for keeping track of date for panels that require dates
	private JPanel datePanel = new JPanel();
	private JComboBox<String> year, month, day;
	
	// For resetting what is shown in table
	private JTable table;
	
	public EmployeePanel(JFrame parentFrame) {
		super();
		this.jf = parentFrame;
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setRowHeight(StaticVar.MENUFONT.getSize());
		table.setFont(StaticVar.TABLEFONT);
		
		// CENTER Text
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridLayout(7,1,10,10));
		setBorder(new EmptyBorder(10,10,10,10));
		
		
		JButton viewEmpInfo = new JButton("Employee Info");
		viewEmpInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("Employee Info");
				
				JScrollPane scrollP = new JScrollPane(table);
				scrollP.setVisible(true);
				
				// Create a panel to hold the label and table
				JPanel panel = new JPanel();
				BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
				panel.setLayout(bl);
				
				// Create Label
				JLabel label = new JLabel("Employee Information", JLabel.CENTER);
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				label.setFont(StaticVar.MENUFONT);
				
				// Create radio buttons
				ButtonGroup buttonGroup = new ButtonGroup();
				JRadioButton allEmpAcc = new JRadioButton("All Employees");
				allEmpAcc.addActionListener(x -> {
					String[] colN = {"First Name",
							 		"Last Name",
							 		"Employee Id",
							 		"SSN",
							 		"Position",
							 		"Salary",
							 		"Hire Date"};
			
					String[] dataOrder = {StaticVar.FIRST_NAME, 
										StaticVar.LAST_NAME, 
										StaticVar.EMPLOYEEE_ID,
										StaticVar.SSN,
										StaticVar.POS,
										StaticVar.SALARY,
										StaticVar.HDATE};
					
					Data data = EmployeeFunctions.viewAllEmps(jf,dataOrder);
					table.setModel(new CustomTableModel(colN, data.dataValues));
					
				});
				allEmpAcc.doClick();
								
				JRadioButton withAcc = new JRadioButton("With Account");
				withAcc.addActionListener(x ->{
					String[] colN = {"First Name",
					 		"Last Name",
					 		"Employee Id"};
	
					String[] dataOrder = {StaticVar.FIRST_NAME, 
								StaticVar.LAST_NAME, 
								StaticVar.EMPLOYEEE_ID};
			
					Data data = EmployeeFunctions.empsWAccts(jf,dataOrder);
					table.setModel(new CustomTableModel(colN, data.dataValues));
				});
				
				JRadioButton abvSal = new JRadioButton("Above Salary");
				abvSal.addActionListener(x -> {
					String[] colN = {"First Name",
					 		"Last Name",
					 		"Employee Id",
					 		"Salary"};
	
					String[] dataOrder = {StaticVar.FIRST_NAME, 
								StaticVar.LAST_NAME, 
								StaticVar.EMPLOYEEE_ID,
								StaticVar.SALARY};
			
					Data data = EmployeeFunctions.empsWAboveAvgSal(jf,dataOrder);
					table.setModel(new CustomTableModel(colN, data.dataValues));
				});
				
				JRadioButton belSal = new JRadioButton("Below Salary");
				belSal.addActionListener(x -> {
					String[] colN = {"First Name",
					 		"Last Name",
					 		"Employee Id",
					 		"Salary"};
	
					String[] dataOrder = {StaticVar.FIRST_NAME, 
								StaticVar.LAST_NAME, 
								StaticVar.EMPLOYEEE_ID,
								StaticVar.SALARY};
			
					Data data = EmployeeFunctions.empsWBelowAvgSal(jf,dataOrder);
					table.setModel(new CustomTableModel(colN, data.dataValues));
				});
				
				buttonGroup.add(allEmpAcc);
				buttonGroup.add(withAcc);
				buttonGroup.add(abvSal);
				buttonGroup.add(belSal);
				
				JPanel radioPanel = new JPanel();
				radioPanel.add(allEmpAcc);
				radioPanel.add(withAcc);
				radioPanel.add(abvSal);
				radioPanel.add(belSal);

				
				// Adding order
				panel.add(label);
				panel.add(radioPanel);
				panel.add(scrollP);
				
				jf.add(panel);
				jf.setSize(StaticVar.WINDIMENSION.width/2, StaticVar.WINDIMENSION.height/2);
				jf.setLocationRelativeTo(null);
				jf.setVisible(true);
			}
		});
		JButton calcEmpSalExp = new JButton("Calculate Employee Net Salary");
		calcEmpSalExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame jf = new JFrame("Salary Info");
				
				Data data = null;
				
				JTextField empid = new JTextField();
				JTextField conempid = new JTextField();
				Object[] message = {
					"Employee ID: ", empid,
					"Confirm Employee ID: ", conempid
				};
				int option = JOptionPane.showConfirmDialog(null, message, "Enter Employee ID", JOptionPane.OK_CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION) {
					
					data = EmployeeFunctions.getNetSalary(jf,empid.getText(), conempid.getText());
				}
				
				
				// Create a panel to hold the label and table
				JPanel panel = new JPanel();
				BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
				panel.setLayout(bl);
				
				// Create Label
				JLabel label = new JLabel("Net Salary For Employee: ", JLabel.CENTER);
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				label.setFont(StaticVar.MENUFONT);
				
				 
				Double yearly = data != null ? data.yearly : 0;
				Double monthly = data != null ? data.monthly : 0;

				JLabel yearLabel = new JLabel("Yearly Net Salary: $" + String.format( "%,.2f", yearly), JLabel.CENTER);				
	            yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	            yearLabel.setFont(StaticVar.MENUFONT);
					
	            JLabel monthLabel = new JLabel("Monthly Net Salary: $" + String.format( "%,.2f", monthly), JLabel.CENTER);			
	            monthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	            monthLabel.setFont(StaticVar.MENUFONT);
				
				
				// Adding order
				panel.add(label);
				panel.add(Box.createRigidArea(new Dimension(5,15)));
				panel.add(yearLabel);
				panel.add(monthLabel);
				
				jf.add(panel);
				
				jf.setSize(StaticVar.WINDIMENSION.width/4, StaticVar.WINDIMENSION.height/4);
				jf.setLocationRelativeTo(null);
				if(yearly != 0) {
					jf.setVisible(true);
				}
				else {
					jf.dispose();
				}
			}
		});


		JButton calcSalExp = new JButton("Calculate Salary Expense");
		calcSalExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				JFrame jf = new JFrame("Salary Info");
				
				// Create a panel to hold the label and table
				JPanel panel = new JPanel();
				BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
				panel.setLayout(bl);
				
				
				// Create Label
				JLabel label = new JLabel("Salary Expense", JLabel.CENTER);
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				label.setFont(StaticVar.MENUFONT);
				
				Data data = EmployeeFunctions.calcSalExpense(jf);
				
                JLabel yearLabel = new JLabel("Yearly Salary Expense: $" + String.format( "%,.2f", data.yearly), JLabel.CENTER);				
                yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				yearLabel.setFont(StaticVar.SALARYFONT);
				
                JLabel monthLabel = new JLabel("Monthly Salary Expense: $" + String.format( "%,.2f", data.monthly), JLabel.CENTER);			
                monthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				monthLabel.setFont(StaticVar.SALARYFONT);
				
				
				// Adding order
				panel.add(label);
				panel.add(Box.createRigidArea(new Dimension(5,15)));
				panel.add(yearLabel);
				panel.add(monthLabel);
				
				jf.add(panel);
				
				jf.setSize(StaticVar.WINDIMENSION.width/4, StaticVar.WINDIMENSION.height/4);
				jf.setLocationRelativeTo(null);
				jf.setVisible(true);
			}
		});
		
		
		// Create hire Employee Button
		JButton hire = new JButton("Hire Employee");
		hire.addActionListener((x) -> {
			JFrame jf = (JFrame) SwingUtilities.getWindowAncestor(this);
			JTextField firstname = new JTextField();
			JTextField lastname = new JTextField();
			JTextField ssn = new JTextField();
			JTextField conssn = new JTextField();
			JTextField salary = new JTextField();
			JTextField job = new JTextField();
			
			// DEFAULT DATE IS Current date
			// For year drop list
			int yearDate = StaticVar.STARTDATE.getYear();
			ArrayList listOfYearsBet = (ArrayList) IntStream.range(0, (int)StaticVar.NUMOFYEARSBET)
					.mapToObj(i -> Integer.toString(yearDate + i))
					.collect(Collectors.toList());
			year = new JComboBox<String>((String[]) listOfYearsBet.toArray(new String[0]));
			int selectedYear = StaticVar.TODAY.getYear(); // today's date
			year.setSelectedItem(Integer.toString(selectedYear));
			year.addActionListener(e -> {
				JComboBox cb = (JComboBox)e.getSource();
				day = StaticFunc.getDayComboBox(Integer.parseInt((String) cb.getSelectedItem()), 
						 month.getSelectedIndex() + 1);
				updateDayList();

			});
			
			
			// For month drop list
			month = new JComboBox<String>(StaticVar.S_MONTH);
			int monthDate = StaticVar.TODAY.getMonthValue(); // JAN
			month.setSelectedIndex(monthDate - 1);;
			month.addActionListener(e -> {
				JComboBox cb = (JComboBox)e.getSource();
				day = StaticFunc.getDayComboBox(Integer.parseInt((String) year.getSelectedItem()), 
						cb.getSelectedIndex() + 1);
				updateDayList();

			});
			
			// For Day drop list
			day = StaticFunc.getDayComboBox(selectedYear, monthDate);
			day.setSelectedItem(Integer.toString(StaticVar.TODAY.getDayOfMonth())); 
			
			datePanel.removeAll();
			datePanel.add(year);
			datePanel.add(month);
			datePanel.add(day);
			
			Object[] message = {
				"First Name: ", firstname,
				"Last Name: ", lastname,
				"SSN: ", ssn,
				"Confirm SSN: ", conssn,
				"Employee Salary: ", salary,
				"Employee Job: ", job,
				"Start Date: ", datePanel
				
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Hire Employee", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				
				String date = day.getSelectedItem() + "-" + month.getSelectedItem() + "-" + year.getSelectedItem();
				EmployeeFunctions.hire(jf, firstname.getText(),lastname.getText(),
					ssn.getText(),conssn.getText(),salary.getText(),job.getText(),date);
			}
		});
		
		
		// Create fire Employee Button

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
			
			Container cardPanel = this.getParent();
			CardLayout c = (CardLayout) cardPanel.getLayout();
			c.show(cardPanel, StaticVar.MENU);
			
			int height = (int)(StaticVar.WINDIMENSION.getHeight()/4);
			int width = (int)(StaticVar.WINDIMENSION.getWidth()/4);
			jf.setSize(width, height);
			jf.setLocationRelativeTo(null);
		});
		
		add(viewEmpInfo);
		add(calcEmpSalExp);
		add(calcSalExp);
		add(hire);
		add(fire);
		add(main);
		
		for(int i = 0; i < getComponentCount(); i++){
			
			if(getComponents()[i] instanceof JButton){
				getComponents()[i].setFont(StaticVar.MENUFONT);
			}
		}
	}
	
	private void updateDayList(){
		
		// since 2 is the index of day
		datePanel.remove(2);
		datePanel.add(day);
		datePanel.revalidate();
		datePanel.repaint();
	}
	
	/*
	private static JTable getTableWithData(String[] colN, Data data){
		JTable table = new JTable(new CustomTableModel(colN, data.dataValues));
		table.setFillsViewportHeight(true);
		table.setRowHeight(StaticVar.MENUFONT.getSize());
		table.setFont(StaticVar.TABLEFONT);
		
		return table;
	}
	*/
	
}
