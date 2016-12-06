package screens;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AdminPanel extends JPanel
{
	private JTabbedPane panel;
	
	private JPanel addSnackPanel;
	private JPanel addSupplierPanel;
	private JPanel editSupplierPanel;
	
	public AdminPanel()
	{
		addSnackPanel = new AddSnackPanel();
		addSupplierPanel = new AddSupplierPanel();
		editSupplierPanel = new EditSupplierPanel();
		
		addSnackPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		addSupplierPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		editSupplierPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		
		panel = new JTabbedPane();
		
		panel.addTab("Add Snack", addSnackPanel);
		panel.addTab("Add Supplier", addSupplierPanel);
		panel.addTab("Edit Supplier", editSupplierPanel);
		
		add(panel);
	}

}
