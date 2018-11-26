package frontend;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the interface the user will use when running
 * the application
 * @author clegg, Yu Jun Zhao
 *
 */

public class Menu extends JFrame{
	
	public static final Font FONT = new Font("Arial", Font.PLAIN, 30);
	
	public Menu(){
		JFrame jf = initializeJF();
		addButtons(jf);
		//jf.pack();
		jf.setVisible(true);
	
	}
	
	/**
	 * Creates an empty JFrame with a GridLayout for the Buttons
	 * @return output The JFrame menu to be displayed
	 */
	private JFrame initializeJF(){
		JFrame menuFrame = new JFrame("Bank Management System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
	    menuFrame.setSize(width/4, height/4);
	
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setLocationRelativeTo(null);
		
		return menuFrame;
	}
	
	//TODO: Add action listeners to buttons 
	//      that call backend functions
	/**
	 * Adds buttons for the backend functions to the JFrame
	 * @param jf The JFrame to add the buttons to
	 */
	private void addButtons(JFrame jf){
		
		//create panel to hold all buttons in flow layout
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		
		//create empManag button
		JButton employeeManagement = new JButton("Employee Management");
		employeeManagement.setAlignmentX(Component.CENTER_ALIGNMENT);
		employeeManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				jf.dispose();

				//call the employee management frame
				JFrame empManagement = new EmployeeFrame();
			}
		});

		
		JButton transactionInfo = new JButton("Transaction Information");
		transactionInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				jf.dispose();

				//call the employee management frame
				JFrame transacInfo = new TransactionFrame();
			}
		});

		
		JButton accountInfo = new JButton("Account Information");
		accountInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				jf.dispose();

				//call the employee management frame
				JFrame accountInfo = new AccountFrame();
			}
		});
		
		buttonPanel.add(employeeManagement);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
		buttonPanel.add(transactionInfo);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
		buttonPanel.add(accountInfo);
		
		// CHANGE THE FONTS
		for(int i = 0; i < buttonPanel.getComponentCount(); i++){
			
			if(buttonPanel.getComponents()[i] instanceof JButton){
				buttonPanel.getComponents()[i].setFont(Menu.FONT);
			}
		}
		
		jf.add(buttonPanel);
	}
}
