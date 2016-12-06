package screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.SQLHandler;

@SuppressWarnings("serial")
public class AddSupplierPanel extends JPanel
{
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton addButton;
	
	private SQLHandler handler;
	
	public AddSupplierPanel() throws ClassNotFoundException
	{
		handler = new SQLHandler();
		
		nameLabel = new JLabel("Name: ");
		nameTextField = new JTextField();
		
		addButton = new JButton(new AbstractAction("Add")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
            	String name = nameTextField.getText();

            	try
            	{
            		String retrieve = "SELECT MAX(Supplier.SupplierId) AS maxID FROM Supplier";
            		
					ResultSet rs = handler.Query(retrieve);
					int id = rs.getInt("maxID") + 1;
					
            		String insert = "INSERT INTO Supplier VALUES ('" + name + "', " + id +")";
					
					handler.Insert(insert);
				}
            	catch (SQLException e)
            	{
					e.printStackTrace();
				}
            	
            	nameTextField.setText("");
            	
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
