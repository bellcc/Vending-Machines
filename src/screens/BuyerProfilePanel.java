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
        
        editButton.setMinimumSize(new Dimension(0, 50));
        editButton.setPreferredSize(new Dimension(0, 50));
        editButton.setMaximumSize(new Dimension(0, 50));

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
        constraint.ipadx = 0;
        constraint.gridx = 0;
        constraint.gridy = 6;
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

        add(panel);
    }
}
