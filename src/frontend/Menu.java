package frontend;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
	
	private JFrame jf = new JFrame("Bank Management System");
	
	public Menu() {
		initialize(jf);
		addButtons(jf);
		jf.setVisible(true);
	}
	
	private void initialize(JFrame jf) {
		jf.setLayout(new BorderLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)(screenSize.getHeight()/4);
		int width = (int)(screenSize.getWidth()/4);
		jf.setSize(width, height);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
	}
	
	private void addButtons(JFrame jf) {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,1,20,20));
		buttonPanel.setBorder(new EmptyBorder(20,20,20,20));
		
		//create empManag button
				JButton employeeManagement = new JButton("Employee Management");
				employeeManagement.setAlignmentX(Component.CENTER_ALIGNMENT);
				employeeManagement.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel employeePanel = new EmployeePanel();
						jf.add(employeePanel, BorderLayout.CENTER);
						buttonPanel.setVisible(false);
						//jf.remove(buttonPanel);
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
						int height = (int)(screenSize.getHeight()/3);
						int width = (int)(screenSize.getWidth()/3);
						jf.setSize(width, height);
						jf.validate();
						jf.repaint();
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
				
				buttonPanel.add(employeeManagement);
				buttonPanel.add(transactionInfo);
				buttonPanel.add(accountInfo);
				
				for(int i = 0; i < buttonPanel.getComponentCount(); i++){
					
					if(buttonPanel.getComponents()[i] instanceof JButton){
						buttonPanel.getComponents()[i].setFont(new Font("Arial", Font.PLAIN, 30));
					}
				}
				
				jf.add(buttonPanel, BorderLayout.CENTER);
	}
}
