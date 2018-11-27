package frontend;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class InfoPanel extends JPanel{
	private JLabel labelName;
	private JButton button;
	private JPanel upperPanel, bottomPanel;

	
	public InfoPanel(String name){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		upperPanel = new JPanel();
		bottomPanel = new JPanel();
		
		labelName = new JLabel(name, JLabel.CENTER);
		labelName.setFont(Menu.FONT);
		
		// Create back Button
		button = new JButton("Back");
		button.addActionListener(e -> {
			CardLayout c1 = (CardLayout) this.getParent().getLayout();
		    c1.show(this.getParent(), EmployeeMainPanel.EMPPANEL);
		});
		
		upperPanel.add(button);
		upperPanel.add(labelName);
		
		
		this.add(upperPanel);
		this.add(bottomPanel);
	}
	
    public void upperPanelAdd(Component part){
    	upperPanel.add(part);
    }
    public void bottomPanelAdd(Component part){
    	bottomPanel.add(part);
    }

	
	
	//public String getName(){return this.name;}
	
}
