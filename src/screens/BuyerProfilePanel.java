package screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    private JButton homeButton;

    public BuyerProfilePanel()
    {
        imageLabel = new JLabel(new ImageIcon("assets/profile_picture.jpg"));
        nameLabel  = new JLabel("Name: ");
        emailLabel = new JLabel("Email Address: ");
        phoneLabel = new JLabel("Phone Number: ");

        imageLabel.setMinimumSize(new Dimension(200, 241));
        imageLabel.setPreferredSize(new Dimension(200, 241));
        imageLabel.setMaximumSize(new Dimension(200, 241));

        nameTextField  = new JTextField("");
        emailTextField = new JTextField("");
        phoneTextField = new JTextField("");

        editButton = new JButton(new AbstractAction("Edit")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                //Toggle text field editability
            }
        });

        homeButton = new JButton(new AbstractAction("Home")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                //Go to the home panel
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(imageLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.ipadx = 100;
        constraint.ipady = 25;
        panel.add(homeButton, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight = 1;
        constraint.gridx = 0;
        constraint.gridy = 6;
        constraint.ipadx = 100;
        constraint.ipady = 25;
        panel.add(editButton, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 1;
        constraint.gridy = 3;
        constraint.ipadx = 50;
        constraint.ipady = 25;
        panel.add(nameLabel, constraint);

        add(panel);
    }
}
