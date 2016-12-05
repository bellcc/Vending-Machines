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

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddSupplierSnackPanel extends JPanel
{
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel typeLabel;
	private JLabel healthyLabel;
	private JLabel costLabel;
	private JLabel quantityLabel;
	private JLabel supplierLabel;
	
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextField typeTextField;
	private JTextField costTextField;
	private JTextField quantityTextField;
	
	private JComboBox healthyComboBox;
	private JComboBox supplierComboBox;
	
	private JButton addButton;
	
	public AddSupplierSnackPanel()
	{
		nameLabel = new JLabel("Name: ");
		priceLabel = new JLabel("Price: ");
		typeLabel = new JLabel("Type: ");
		healthyLabel = new JLabel("Healthy: ");
		costLabel = new JLabel("Cost: ");
		quantityLabel = new JLabel("Quantity: ");
		supplierLabel = new JLabel("Supplier: ");
		
		nameTextField = new JTextField();
		priceTextField = new JTextField();
		typeTextField = new JTextField();
		costTextField = new JTextField();
		quantityTextField = new JTextField();
		
		String isHealthy[] = {"Yes", "No"};
		healthyComboBox = new JComboBox<>(isHealthy);
		
		//TODO Retrieval query for suppliers
		String suppliers[] = {"You", "Me", "Her"};
		supplierComboBox = new JComboBox<>(suppliers);
		
		addButton = new JButton(new AbstractAction("Add")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {	
            	String name = nameTextField.getText();
            	double price = Double.parseDouble(priceTextField.getText());
            	String type = typeTextField.getText();
            	String healthy = (String) healthyComboBox.getSelectedItem();
            	double cost = Double.parseDouble(costTextField.getText());
            	double quantity = Double.parseDouble(quantityTextField.getText());
            	String supplier = (String) supplierComboBox.getSelectedItem();
            	
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
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 4;
        panel.add(costLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 4;
        panel.add(costTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 5;
        panel.add(quantityLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 5;
        panel.add(quantityTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 6;
        panel.add(supplierLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 6;
        panel.add(supplierComboBox, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 7;
        constraint.insets = new Insets(10, 0, 0, 0);
        panel.add(addButton, constraint);
        
		add(panel);
	}
}
