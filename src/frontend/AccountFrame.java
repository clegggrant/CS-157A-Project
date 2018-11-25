package frontend;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AccountFrame extends JFrame{
	
public AccountFrame() {
		
		setSize(300,250);
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		buttonPanel.add(new JButton("View Employees With Accounts"));
		buttonPanel.add(new JButton("View Employees With Above Avg Salary"));
		buttonPanel.add(new JButton("View Employees With Below Avg Salary"));
		buttonPanel.add(new JButton("Calculate Salary Expense"));
		buttonPanel.add(new JButton("Hire Employees"));
		buttonPanel.add(new JButton("Fire Employees"));
		
		
		JButton mainMenu = new JButton("Main Menu");
		buttonPanel.add(mainMenu);
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose of the current frame
				dispose();

				//call the employee management frame
				JFrame menu = new Menu();
			}
		});
		
		
		add(buttonPanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
