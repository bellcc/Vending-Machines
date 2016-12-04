package screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

@SuppressWarnings("serial")
public class BuyerProfilePanel extends JPanel
{
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;

    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField phoneTextField;

    private JButton editButton;
    
    private JTable historyTable;
    private JScrollPane historyPane;

    public BuyerProfilePanel()
    {
    	URL url = BuyerProfilePanel.class.getResource("/assets/profile_picture.jpg");
        imageLabel = new JLabel(new ImageIcon(url));
        nameLabel  = new JLabel("Name: ");
        emailLabel = new JLabel("Email Address: ");
        phoneLabel = new JLabel("Phone Number: ");

        imageLabel.setMinimumSize(new Dimension(200, 241));
        imageLabel.setPreferredSize(new Dimension(200, 241));
        imageLabel.setMaximumSize(new Dimension(200, 241));

        nameTextField  = new JTextField("");
        emailTextField = new JTextField("");
        phoneTextField = new JTextField("");
        
        nameTextField.setEditable(false);
        emailTextField.setEditable(false);
        phoneTextField.setEditable(false);

        editButton = new JButton(new AbstractAction("Edit")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                //Toggle text field editability

            	
            	if(nameTextField.isEditable() == true)
            	{
                	String name = nameTextField.getText();
                	String email = emailTextField.getText();
                	String phone = phoneTextField.getText();
                	
                	//TODO Update user information in database
                	
                    nameTextField.setEditable(false);
                    emailTextField.setEditable(false);
                    phoneTextField.setEditable(false);
            	}
            	else
            	{
                    nameTextField.setEditable(true);
                    emailTextField.setEditable(true);
                    phoneTextField.setEditable(true);
            	}
            }
        });
        
        //TODO Retrieve buy history
        String columns[] = {"Name", "Type", "Healthy" , "Price"};
        
        Object[][] data = {{"Kick Start", "Drink", "No", "1.00"}, 
        				   {"Cheetos", "Chip" , "No" , "1.50"}};
        				
        historyTable = new JTable(data, columns);
        historyTable.setPreferredScrollableViewportSize(new Dimension(500, 250));
        historyTable.setFillsViewportHeight(true);
        
        historyPane = new JScrollPane(historyTable);
        
        editButton.setMinimumSize(new Dimension(15, 45));
        editButton.setPreferredSize(new Dimension(15, 45));
        editButton.setMaximumSize(new Dimension(15, 45));
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 5;
        constraint.ipady = 25;
        constraint.ipadx = 50;
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(imageLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 0;
        constraint.ipadx = 50;
        constraint.gridx = 3;
        constraint.gridy = 0;
        constraint.insets = new Insets(0, 5, -10, 5);
        panel.add(editButton, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 25;
        constraint.gridx = 1;
        constraint.gridy = 0;
        panel.add(nameLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 2;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 0, 0, 0);
        panel.add(nameTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 25;
        constraint.gridx = 1;
        constraint.gridy = 1;
        panel.add(emailLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 2;
        constraint.gridy = 1;
        panel.add(emailTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 25;
        constraint.gridx = 1;
        constraint.gridy = 2;
        panel.add(phoneLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 2;
        constraint.gridy = 2;
        panel.add(phoneTextField, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.gridwidth = 4;
        constraint.ipady = 0;
        constraint.ipadx = 0;
        constraint.gridx = 0;
        constraint.gridy = 7;
        panel.add(historyPane, constraint);

        add(panel);
    }
}
