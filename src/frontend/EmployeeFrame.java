package frontend;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EmployeeFrame extends JFrame{
	
	// this contains all the panel necessary for scene transition
	private EmployeeMainPanel employeePanel;


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
