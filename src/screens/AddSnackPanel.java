/*
 * Author    : Christopher Bell
 * Professor : Dr. Inclezan
 * Date      : 12-5-2016
 * Info      : 
 */

package screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.SQLHandler;

@SuppressWarnings("serial")
public class AddSnackPanel extends JPanel
{
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel typeLabel;
	private JLabel healthyLabel;
	
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextField typeTextField;
	
	private JComboBox<String> healthyComboBox;
	
	private JButton addButton;
	
	private SQLHandler handler;
	
	public AddSnackPanel() throws ClassNotFoundException
	{
		handler = new SQLHandler();
		
		nameLabel = new JLabel("Name: ");
		priceLabel = new JLabel("Price: ");
		typeLabel = new JLabel("Type: ");
		healthyLabel = new JLabel("Healthy: ");
		
		nameTextField = new JTextField();
		priceTextField = new JTextField();
		typeTextField = new JTextField();
		
		String isHealthy[] = {"Yes", "No"};
		healthyComboBox = new JComboBox<>(isHealthy);
		
		addButton = new JButton(new AbstractAction("Add")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {	
            	String name = nameTextField.getText();
            	String price = priceTextField.getText();
            	String type = typeTextField.getText();
            	String healthy = (String) healthyComboBox.getSelectedItem();
            	
            	try
            	{
            		String retrieve = "SELECT MAX(Snack.SnackId) AS maxID FROM Snack";
            		
					ResultSet rs = handler.Query(retrieve);
					int id = rs.getInt("maxID") + 1;
					
            		String insert = "INSERT INTO Snack VALUES (" + id + ", " + price + 
            				        ", '" + type + "', '" + name + "', '" + healthy + "')";
					
					handler.Insert(insert);
				}
            	catch (SQLException e)
            	{
					e.printStackTrace();
				}
            	
            	nameTextField.setText("");
            	priceTextField.setText("");
            	typeTextField.setText("");
            	healthyComboBox.setSelectedItem("Yes");
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
        constraint.insets = new Insets(5, 10, 5, 10);
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
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 1;
        panel.add(priceLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 1;
        panel.add(priceTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 2;
        panel.add(typeLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 2;
        panel.add(typeTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 3;
        panel.add(healthyLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 3;
        panel.add(healthyComboBox, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 6;
        constraint.insets = new Insets(10, 0, 0, 0);
        panel.add(addButton, constraint);
        
		add(panel);
	}
}
