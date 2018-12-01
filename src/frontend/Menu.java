package frontend;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
	public static final Dimension WINDIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	public static final String MENU = "MENU";
	public static final String EMPANEL = "EMPLOYEE PANEL";
	
	private JFrame jf = new JFrame("Bank Management System");
	private JPanel cardPanel = new JPanel(new CardLayout());; // for switching panels.
	

	public Menu() {
		
		initialize();
		
		cardPanel.add(getMenuPanel(), MENU);		
		cardPanel.add(new EmployeePanel(), EMPANEL);
		jf.add(cardPanel, BorderLayout.CENTER);
		
		jf.setVisible(true);
	}
	
	private void initialize() {
		jf.setLayout(new BorderLayout());

		int height = (int)(WINDIMENSION.getHeight()/4);
		int width = (int)(WINDIMENSION.getWidth()/4);
		jf.setSize(width, height);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
	}
	
	private JPanel getMenuPanel() {
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(3,1,20,20));
		menuPanel.setBorder(new EmptyBorder(20,20,20,20));
		
		//create empManag button
			JButton employeeManagement = new JButton("Employee Management");
			employeeManagement.setAlignmentX(Component.CENTER_ALIGNMENT);
			employeeManagement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int height = (int)(WINDIMENSION.getHeight()/3);
					int width = (int)(WINDIMENSION.getWidth()/3);
					jf.setSize(width, height);
					jf.setLocationRelativeTo(null);

					CardLayout c = (CardLayout)cardPanel.getLayout();
					c.show(cardPanel, EMPANEL);
										
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
					menuPanel.getComponents()[i].setFont(new Font("Arial", Font.PLAIN, 30));
				}
			}
			
			return menuPanel;
	}
}
