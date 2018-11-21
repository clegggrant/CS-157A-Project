package frontend;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the interface the user will use when running
 * the application
 * @author clegg
 *
 */

public class Menu extends JFrame{
	
	public final Font font = new Font("Arial", Font.PLAIN, 20);
	
	public Menu(){
		JFrame jf = initializeJF();
		addButtons(jf);
		jf.setVisible(true);
	}
	
	/**
	 * Creates an empty JFrame with a GridLayout for the Buttons
	 * @return output The JFrame menu to be displayed
	 */
	private JFrame initializeJF(){
		JFrame output = new JFrame("Bank Management System");
		output.setSize(300,130);
	
		output.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return output;
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
		buttonPanel.setLayout(new FlowLayout());
		
		
		//create empManag button
		JButton employeeManagement = new JButton("Employee Management");
		buttonPanel.add(employeeManagement);
		//create action listener for the empManag button
		employeeManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				jf.dispose();

				//call the employee management frame
				JFrame empManagement = new EmployeeFrame();
			}
		});
		
		
		JButton transactionInfo = new JButton("Transaction Information");
		buttonPanel.add(transactionInfo);
		transactionInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				jf.dispose();

				//call the employee management frame
				JFrame transacInfo = new TransactionFrame();
			}
		});
		

		JButton accountInfo = new JButton("Account Information");
		buttonPanel.add(accountInfo);
		accountInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				jf.dispose();

				//call the employee management frame
				JFrame accountInfo = new AccountFrame();
			}
		});
		
		
		for(int i = 0; i < jf.getContentPane().getComponentCount(); i++){
			
			if(jf.getContentPane().getComponents()[i] instanceof JButton){
				jf.getContentPane().getComponents()[i].setFont(font);
			}
		}
		
		jf.add(buttonPanel);
	}
}
