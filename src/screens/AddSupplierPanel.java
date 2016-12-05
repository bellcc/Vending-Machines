package screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddSupplierPanel extends JPanel
{
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton addButton;
	
	public AddSupplierPanel()
	{
		nameLabel = new JLabel("Name: ");
		nameTextField = new JTextField();
		
		addButton = new JButton(new AbstractAction("Add")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
            	String name = nameTextField.getText();
            	
            	//TODO SQL Insert statement here
            }
        });
		
		JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(nameLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 0;
        panel.add(nameTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 0, 0, 0);
        panel.add(addButton, constraint);
        
        add(panel);
	}
}
