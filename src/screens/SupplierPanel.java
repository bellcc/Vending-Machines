package screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SupplierPanel extends JPanel {
	
	private JTextField enterSupplier;
	private JTextField enterSnackName;
	private JTextField enterSnackType;
	private JTextField enterSnackIs_Healthy;
	
	private JTextField changeSupplier;
	private JComboBox<String> suppliers;
	
	private JSpinner enterSnackPrice;
	private JSpinner enterSnackCost;
	private JSpinner enterSnackQty;
	
	private JComboBox<String> snackSuppliers;
	
	private boolean failed;
	private String addString;
	private String selectedSupplier;
	private String editString;
	
	private SQLhandler handle;
	
	public SupplierPanel() throws SQLException {
		
		try
		{
			handle = new SQLhandler();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//setSize(400,300);
		JTabbedPane tabs = new JTabbedPane();
		JPanel addSupplier = new JPanel();
		JLabel addText = new JLabel("Add Supplier");
		addSupplier.add(addText);
		enterSupplier = new JTextField(15);
		addSupplier.add(enterSupplier);
		JButton supplierButton = new JButton("Add");
		supplierButton.addActionListener(new AddAction());
		addSupplier.add(supplierButton);
		
		
		JPanel editSupplier = new JPanel();
		changeSupplier = new JTextField(15);
		suppliers = new JComboBox<String>();
		suppliers.addItemListener(new ItemAction());
		ResultSet rset = handle.Query("SELECT * FROM Supplier");
		
		while(rset.next()) {
			suppliers.addItem(rset.getString("Name"));
		}
		suppliers.setSize(200, 75);
		
		
		addSupplier.add(enterSupplier);
		JButton changeButton = new JButton("Change");
		changeButton.addActionListener(new EditAction());
		editSupplier.add(suppliers);
		editSupplier.add(changeSupplier);
		editSupplier.add(changeButton);
		
		
		JPanel addSnack = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
		
		JLabel addSnackName = new JLabel("Snack Name: ");
		JLabel addSnackPrice = new JLabel("Snack Price: ");
		JLabel addSnackType = new JLabel("Snack Type: ");
		JLabel addSnackIs_Healthy = new JLabel("Is Healthy: ");
		JLabel addSnackCost = new JLabel("Snack Cost: ");
		JLabel addSnackQty = new JLabel("Snack Quantity: ");
		
		addSnack.add(addSnackName);
		enterSnackName = new JTextField(15);
		addSnack.add(enterSnackName);
		

		addSnack.add(addSnackPrice);
		enterSnackPrice = new JSpinner();
		addSnack.add(enterSnackPrice);
		

		addSnack.add(addSnackType);
		enterSnackType = new JTextField(15);
		addSnack.add(enterSnackType);
		

		addSnack.add(addSnackIs_Healthy);
		enterSnackIs_Healthy = new JTextField(15);
		addSnack.add(enterSnackIs_Healthy);
		

		addSnack.add(addSnackCost);
		enterSnackCost = new JSpinner();
		addSnack.add(enterSnackCost);
		

		addSnack.add(addSnackQty);
		enterSnackQty = new JSpinner();
		addSnack.add(enterSnackQty);
		
		snackSuppliers = new JComboBox<>();
		ResultSet rset1 = handle.Query("SELECT * FROM Supplier");
		while(rset1.next()) {
			snackSuppliers.addItem(rset1.getString("Name"));
		}
		addSnack.add(snackSuppliers);
		
		JButton snackButton = new JButton("Add");
		snackButton.addActionListener(new AddAction());
		addSnack.add(snackButton);
		
		
		tabs.addTab("Add Supplier", addSupplier);
		tabs.addTab("Edit Supplier", editSupplier);
		tabs.addTab("Add Snack", addSnack);
		add(tabs);
		this.setVisible(true);
	}
	
	private class AddAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0){
			addString = enterSupplier.getText();
			if(addString != null && !addString.isEmpty()){
				failed = handle.Insert(String.format("INSERT INTO Supplier VALUES (%d, '%s')",addString.hashCode(),addString));
				addString = null;
			}
			enterSupplier.setText("");
			
			suppliers.removeAllItems();
			try {
				ResultSet rset = handle.Query("SELECT * FROM Supplier");
				while(rset.next()) {
					suppliers.addItem(rset.getString("Name"));
				}
				ResultSet rset1 = handle.Query("SELECT * FROM Supplier");
				while(rset1.next()) {
					snackSuppliers.addItem(rset1.getString("Name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private class EditAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0){
			editString = changeSupplier.getText();
			if(editString != null && !editString.isEmpty() && selectedSupplier != null){
				try {
				ResultSet rset = handle.Query(String.format("SELECT SupplierId FROM Supplier WHERE Name = '%s'", selectedSupplier));
				failed = handle.Insert(String.format("UPDATE Supplier SET Name = '%s' WHERE SupplierId = %d", editString, rset.getInt("SupplierId")));
				editString = null;
				selectedSupplier = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			suppliers.removeAllItems();
			try {
				ResultSet rset = handle.Query("SELECT * FROM Supplier");
				while(rset.next()) {
					suppliers.addItem(rset.getString("Name"));
				}
				ResultSet rset1 = handle.Query("SELECT * FROM Supplier");
				while(rset1.next()) {
					snackSuppliers.addItem(rset1.getString("Name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			changeSupplier = null;
		}
		
	}
	
	private class ItemAction implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			selectedSupplier = (String) suppliers.getSelectedItem();
			changeSupplier.setText(selectedSupplier);
		}
		
	}
	
	private class addSnack implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = enterSnackName.getText();
			double price = (double) enterSnackPrice.getValue();
			String type = enterSnackType.getText();
			double cost = (double) enterSnackCost.getValue();
			int qty = (int) enterSnackQty.getValue();
			String supplier = (String) snackSuppliers.getSelectedItem();
			
			try {
				ResultSet rset = handle.Query(String.format("SELECT SupplierId FROM Supplier WHERE Name = '%s'", selectedSupplier));
				handle.Insert(String.format("INSERT INTO Snack VALUES(%d,%f,%s,%s,%b)", (name+type).hashCode(),price,type,name,false));
				handle.Insert(String.format("INSERT INTO Supplies VALUES(%f,%d,%d,%d)", cost,qty,rset.getInt(0),(name+type).hashCode()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
