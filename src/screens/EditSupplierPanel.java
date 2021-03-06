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
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import tools.SQLHandler;
import tools.Supplier;

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
	
	private SQLHandler handler;
	
	private Supplier currentSupplier;
	
	public EditSupplierPanel() throws ClassNotFoundException
	{
		handler = new SQLHandler();
		
		panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints constraint = new GridBagConstraints();
        
		supplierLabel = new JLabel("Supplier: ");
		
		supplierComboBox = new JComboBox<>();
		updateSupplierComboBox();
		updateCurrentSupplier();
		
		supplierComboBox.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	updateCurrentSupplier();
		    	updateSupplierSnackComboBox();
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
            	String name = nameTextField.getText().trim();
            	
            	String update = "UPDATE Supplier "
						      + "SET Name = '" + name
						      + "' WHERE SupplierId = " + currentSupplier.getId();
				
				handler.Insert(update);
				
				nameTextField.setText("");
				
				currentSupplier.setName(name);
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
        updateAllSnackComboBox();
        
		addButton = new JButton(new AbstractAction("Add")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {   
            	String name = (String) supplierComboBox.getSelectedItem();
            	String cost = costTextField.getText();
            	String quantity = quantityTextField.getText();
            	String snack = (String) allSnackComboBox.getSelectedItem();
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
            	
            }
        });
        		
        supplierSnackComboBox = new JComboBox<>();
        updateSupplierSnackComboBox();
        
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
	
	private void updateCurrentSupplier()
	{
		Supplier newSupplier;
		
    	try
    	{
    		String name = (String) supplierComboBox.getSelectedItem();    		
    		String retrieve = "SELECT * "
    				        + "FROM Supplier "
    				        + "WHERE Supplier.Name = '" + name + "'";
    		
			ResultSet rs = handler.Query(retrieve);
			int id = rs.getInt("SupplierId");
			String supplierName = rs.getString("Name");
			
			newSupplier = new Supplier(id, supplierName);
			this.currentSupplier = newSupplier;

		}
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}		
	}

	private void updateSupplierComboBox()
	{	
    	try
    	{
    		String retrieve = "SELECT Supplier.Name "
    				        + "FROM Supplier";
    		
			ResultSet rs = handler.Query(retrieve);
			
			supplierComboBox.removeAllItems();			
			
			while(rs.next())
			{
				supplierComboBox.addItem(rs.getString("Name"));
			}
						
		}
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
	}
	
	private void updateAllSnackComboBox()
	{
    	try
    	{
    		String retrieve = "SELECT Snack.Name "
    				        + "FROM Snack";
    		
			ResultSet rs = handler.Query(retrieve);
			
			allSnackComboBox.removeAllItems();			
			
			while(rs.next())
			{
				allSnackComboBox.addItem(rs.getString("Name"));
			}
						
		}
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
	}
	
	private void updateSupplierSnackComboBox()
	{
    	try
    	{
    		String retrieve = "SELECT Supplies.SnackId "
    				         + "FROM Supplies "
    				         + "WHERE Supplies.SupplierId = " + currentSupplier.getId();
    		
			ResultSet rs = handler.Query(retrieve);
			
			supplierSnackComboBox.removeAllItems();			
			
			ArrayList<Integer> nums = new ArrayList<>();
			while(rs.next())
			{
				int id = rs.getInt("SnackId");
				nums.add(id);
			}
			
			for(int i=0;i<nums.size();i++)
			{
				retrieve = "SELECT Snack.Name "
						 + "FROM Snack "
						 + "WHERE Snack.SnackId = " + nums.get(i);
				rs = handler.Query(retrieve);
				
				supplierSnackComboBox.addItem(rs.getString("Name"));
			}
						
		}
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
	}
}
