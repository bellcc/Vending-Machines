package screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class EditSupplierPanel extends JPanel
{
	private JLabel supplierLabel;
	private JLabel nameLabel;
	private JLabel snackLabel;
	private JLabel costLabel;
	private JLabel quantityLabel;
	
	private JTextField nameTextField;
	private JTextField costTextField;
	private JTextField quantityTextField;
	
	private JComboBox<String> supplierComboBox;
	private JComboBox<String> allSnackComboBox;
	private JComboBox<String> supplierSnackComboBox;
	
	private JButton updateButton;
	private JButton addButton;
	private JButton removeButton;
	
	private JPanel panel;
	private JPanel supplierPanel;
	private JPanel addSnackPanel;
	private JPanel removeSnackPanel;
	
	public EditSupplierPanel()
	{
		panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints constraint = new GridBagConstraints();
        
		supplierLabel = new JLabel("Supplier: ");
		
		supplierComboBox = new JComboBox<>();
		
    	String dbFilename = "project.db";
    	
    	try
    	{
			Class.forName("org.sqlite.JDBC");
		}
    	catch (ClassNotFoundException e)
    	{
			e.printStackTrace();
		}
    	
    	Connection connection = null;
    	
    	try
    	{
    		String query = "SELECT Supplier.Name FROM Supplier";
    		
    		connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilename);
    		
    		Statement statement = connection.createStatement();
    		statement.setQueryTimeout(30);
    		
    		ResultSet rs = statement.executeQuery(query);

    		while(rs.next())
    		{
    			supplierComboBox.addItem(rs.getString("Name"));
    		}
    		
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	finally
    	{
    		try
    		{
    			if (connection != null)
    			{
    				connection.close ();
    			}
    		}
    		catch (SQLException e)
    		{
    			System.err.println (e);
    		}
    	}
		
		supplierComboBox.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	updateSupplierSnacks((String) supplierComboBox.getSelectedItem());
		    }
		});
		
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(supplierLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(5, 10, 5, 10);
        panel.add(supplierComboBox, constraint);
        
        supplierPanel = new JPanel(new GridBagLayout());
        supplierPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Update Supplier Information"));
		
		nameLabel = new JLabel("Name: ");
		nameTextField = new JTextField();
		updateButton = new JButton(new AbstractAction("Update")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
            	String name = nameTextField.getText();
            	String supplier = (String) supplierComboBox.getSelectedItem();
            	
            	try
            	{
        			Class.forName("org.sqlite.JDBC");
        		}
            	catch (ClassNotFoundException e)
            	{
        			e.printStackTrace();
        		}
            	
            	Connection connection = null;
            	    	
            	try
            	{
            		String query = "SELECT Supplier.SupplierId FROM Supplier WHERE Supplier.Name = '" + supplier + "'";
            		
            		connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilename);
            		
            		Statement statement = connection.createStatement();
            		statement.setQueryTimeout(30);
            		
            		ResultSet rs = statement.executeQuery(query);
            		int id = rs.getInt("SupplierId");
            		
            		String update = "UPDATE Supplier SET Name = '" + name + "' WHERE SupplierId = " + id;
            		statement.execute(update);
            		
            	}
            	catch(SQLException e)
            	{
            		System.out.println(e.getMessage());
            	}
            	finally
            	{
            		try
            		{
            			if (connection != null)
            			{
            				connection.close ();
            			}
            		}
            		catch (SQLException e)
            		{
            			System.err.println (e);
            		}
            	}
            }
        });
		
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 0;
        supplierPanel.add(nameLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 0;
        supplierPanel.add(nameTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 1;
        supplierPanel.add(updateButton, constraint);
		
        addSnackPanel = new JPanel(new GridBagLayout());
        addSnackPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Add Snack"));
		
        snackLabel = new JLabel("Snack: ");
        costLabel = new JLabel("Cost: ");
        quantityLabel = new JLabel("Quantity: ");
        
        costTextField = new JTextField();
        quantityTextField = new JTextField();
        
        allSnackComboBox = new JComboBox<>();
            	
    	try
    	{
			Class.forName("org.sqlite.JDBC");
		}
    	catch (ClassNotFoundException e)
    	{
			e.printStackTrace();
		}
    	    	
    	try
    	{
    		String query = "SELECT Snack.Name FROM Snack";
    		
    		connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilename);
    		
    		Statement statement = connection.createStatement();
    		statement.setQueryTimeout(30);
    		
    		ResultSet rs = statement.executeQuery(query);
    		
    		while(rs.next())
    		{
    			allSnackComboBox.addItem(rs.getString("Name"));
    		}
    		
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	finally
    	{
    		try
    		{
    			if (connection != null)
    			{
    				connection.close ();
    			}
    		}
    		catch (SQLException e)
    		{
    			System.err.println (e);
    		}
    	}
        
		addButton = new JButton(new AbstractAction("Add")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {   
            	String name = (String) supplierComboBox.getSelectedItem();
            	String cost = costTextField.getText();
            	String quantity = quantityTextField.getText();
            	String snack = (String) allSnackComboBox.getSelectedItem();
            	
            	try
            	{
        			Class.forName("org.sqlite.JDBC");
        		}
            	catch (ClassNotFoundException e)
            	{
        			e.printStackTrace();
        		}
            	
            	Connection connection = null;
            	    	
            	try
            	{
            		String query1 = "SELECT Supplier.SupplierId FROM Supplier WHERE Supplier.Name = '" + name + "'";
            		String query2 = "SELECT Snack.SnackId FROM Snack WHERE Snack.Name = '" + snack + "'";
            		connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilename);
            		
            		Statement statement = connection.createStatement();
            		statement.setQueryTimeout(30);
            		
            		ResultSet rs = statement.executeQuery(query1);
            		int id = rs.getInt("SupplierId");
            		System.out.println("ID: " + id);
            		
            		rs = statement.executeQuery(query2);
            		int snackId = rs.getInt("SnackId");
            		System.out.println("SNACKID: " + snackId);

            		String query3 = "INSERT INTO Supplies VALUES (" + cost + ", " + quantity + ", " + id + ", " + snackId + ")";
            		statement.execute(query3);
            		
            	}
            	catch(SQLException e)
            	{
            		System.out.println(e.getMessage());
            	}
            	finally
            	{
            		try
            		{
            			if (connection != null)
            			{
            				connection.close ();
            			}
            		}
            		catch (SQLException e)
            		{
            			System.err.println (e);
            		}
            	}
            }
        });
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 0;
        constraint.ipadx = 0;
        constraint.gridx = 0;
        constraint.gridy = 0;
        addSnackPanel.add(snackLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 0;
        constraint.ipadx = 0;
        constraint.gridx = 1;
        constraint.gridy = 0;
        addSnackPanel.add(allSnackComboBox, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 1;
        addSnackPanel.add(costLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 1;
        addSnackPanel.add(costTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 2;
        addSnackPanel.add(quantityLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 2;
        addSnackPanel.add(quantityTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 3;
        addSnackPanel.add(addButton, constraint);
        
        removeSnackPanel = new JPanel(new GridBagLayout());
        removeSnackPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Remove Snack"));
        
		removeButton = new JButton(new AbstractAction("Remove")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {            	
            	String name = (String) supplierComboBox.getSelectedItem();
            	String snack = (String) supplierSnackComboBox.getSelectedItem();
            	
            	try
            	{
        			Class.forName("org.sqlite.JDBC");
        		}
            	catch (ClassNotFoundException e)
            	{
        			e.printStackTrace();
        		}
            	
            	Connection connection = null;
            	    	
            	try
            	{
            		String query1 = "SELECT Supplier.SupplierId FROM Supplier WHERE Supplier.Name = '" + name + "'";
            		String query2 = "SELECT Snack.SnackId FROM Snack WHERE Snack.Name = '" + snack + "'";
            		connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilename);
            		
            		Statement statement = connection.createStatement();
            		statement.setQueryTimeout(30);
            		
            		ResultSet rs = statement.executeQuery(query1);
            		int id = rs.getInt("SupplierId");
            		System.out.println("Supplier Id: " + id);
            		
            		rs = statement.executeQuery(query2);
            		int snackId = rs.getInt("SnackId");
            		System.out.println("Snack Id: " + snackId);

            		
            		String query3 = "DELETE FROM Supplies WHERE Supplies.SupplierId = '" + id + "' AND Supplies.SnackId = '" + snackId + "'";
            		statement.execute(query3);
            		
            	}
            	catch(SQLException e)
            	{
            		System.out.println(e.getMessage());
            	}
            	finally
            	{
            		try
            		{
            			if (connection != null)
            			{
            				connection.close();
            			}
            		}
            		catch (SQLException e)
            		{
            			System.err.println(e);
            		}
            	}
            }
        });
        		
        supplierSnackComboBox = new JComboBox<>();
        updateSupplierSnacks((String) supplierComboBox.getSelectedItem());
		        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 0;
        removeSnackPanel.add(snackLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 0;
        removeSnackPanel.add(supplierSnackComboBox, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.ipady = 15;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 1;
        removeSnackPanel.add(removeButton, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.ipady = 0;
        constraint.ipadx = 0;
        constraint.gridx = 0;
        constraint.gridy = 1;
        panel.add(supplierPanel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.ipady = 0;
        constraint.ipadx = 0;
        constraint.gridx = 0;
        constraint.gridy = 2;
        panel.add(addSnackPanel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.ipady = 0;
        constraint.ipadx = 0;
        constraint.gridx = 0;
        constraint.gridy = 3;
        panel.add(removeSnackPanel, constraint);
        
		add(panel);
	}

	private void updateSupplierSnacks(String name)
	{
    	String dbFilename = "project.db";
    	
    	try
    	{
			Class.forName("org.sqlite.JDBC");
		}
    	catch (ClassNotFoundException e)
    	{
			e.printStackTrace();
		}
    	
    	Connection connection = null;
    	
    	try
    	{
    		String supplierId = "SELECT Supplier.SupplierId FROM Supplier WHERE Supplier.Name = '" + name + "'";
    		
    		connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilename);
    		
    		Statement statement = connection.createStatement();
    		statement.setQueryTimeout(30);
    		
    		ResultSet rs = statement.executeQuery(supplierId);
    		int id = rs.getInt("SupplierId");
    		
    		String snackCount = "SELECT COUNT(Supplies.SnackId) AS snackCount FROM Supplies WHERE Supplies.SupplierId = '" + id + "'";
    		rs = statement.executeQuery(snackCount);
    		int count = rs.getInt("snackCount");
    		
    		String snackList = "SELECT Snack.Name FROM Snack INNER JOIN Supplies ON Snack.SnackId = Supplies.SnackId WHERE Supplies.SupplierId = '" + id + "'";
    		rs = statement.executeQuery(snackList);
    		
    		String snacks[] = new String[count];
    		
    		supplierSnackComboBox.removeAllItems();
    		
    		while(rs.next())
    		{
    			supplierSnackComboBox.addItem(rs.getString("Name"));
    		}
    		    		
    		System.out.println(snacks.length);
    		
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	finally
    	{
    		try
    		{
    			if (connection != null)
    			{
    				connection.close ();
    			}
    		}
    		catch (SQLException e)
    		{
    			System.err.println (e);
    		}
    	}
	}
}
