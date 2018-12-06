package frontend;

import javax.swing.*;
import javax.swing.border.*;
import common_util.StaticVar;

import java.awt.*;
import java.awt.event.*;

/*
 * 	The main menu of the application
 * 	
 *  author: Grant Clegg, Monty Saengsavang, Yu Jun Zhao
 * 
 */
public class Menu {
	
	// The main frame
	private JFrame jf = new JFrame("Bank Management System");
	
	// For switching panels.
	private JPanel cardPanel = new JPanel(new CardLayout());
	

	public Menu() {
		
		initialize();
		
		cardPanel.add(getMenuPanel(), StaticVar.MENU);		
		cardPanel.add(new EmployeePanel(jf), StaticVar.EMPANEL);
		
		jf.add(cardPanel, BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	private void initialize() {
		jf.setLayout(new BorderLayout());

		int height = (int)(StaticVar.WINDIMENSION.getHeight()/4);
		int width = (int)(StaticVar.WINDIMENSION.getWidth()/4);
		jf.setSize(width, height);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
	}
	
	private JPanel getMenuPanel() {
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(3,1,20,20));
		menuPanel.setBorder(new EmptyBorder(20,20,20,20));
		
			//Link to employee panel
			JButton employeeManagement = new JButton("Employee Management");
			employeeManagement.setAlignmentX(Component.CENTER_ALIGNMENT);
			employeeManagement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int height = (int)(StaticVar.WINDIMENSION.getHeight()/3);
					int width = (int)(StaticVar.WINDIMENSION.getWidth()/3);
					jf.setSize(width, height);
					jf.setLocationRelativeTo(null);

					CardLayout c = (CardLayout)cardPanel.getLayout();
					c.show(cardPanel, StaticVar.EMPANEL);
										
				}
			});
			
			
			
			JButton transactionInfo = new JButton("Transaction Information");
			transactionInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
			transactionInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(jf, "Feature not yet implemented!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			});

			
			JButton accountInfo = new JButton("Account Information");
			accountInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
			accountInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(jf, "Feature not yet implemented!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			});
			
			menuPanel.add(employeeManagement);
			menuPanel.add(transactionInfo);
			menuPanel.add(accountInfo);
			
			for(int i = 0; i < menuPanel.getComponentCount(); i++){
				
				if(menuPanel.getComponents()[i] instanceof JButton){
					menuPanel.getComponents()[i].setFont(StaticVar.MENUFONT);
				}
			}
			
			return menuPanel;
	}
}
