package screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.net.URL;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel
{
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel imageLabel;

    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private JButton loginButton;
    private JButton signUpButton;

    public LoginPanel()
    {
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        
        URL url = LoginPanel.class.getResource("/assets/vending_machine.png");
        imageLabel = new JLabel(new ImageIcon(url));

        usernameLabel.setBackground(Color.WHITE);

        usernameTextField = new JTextField("");
        passwordTextField = new JTextField("");

        loginButton = new JButton(new AbstractAction("Login") 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String username = usernameTextField.getText().trim();
                String password = passwordTextField.getText().trim();

                if(username.equals("") || password.equals(""))
                {
                    String title = "Authentification Error";
                    String message = "The username and/or password field must not be empty.";
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
                }

                //Authentification
            }
        });

        signUpButton = new JButton(new AbstractAction("Sign Up")
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                //Sign up code here
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 3;
        panel.add(imageLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.ipadx = 50;
        constraint.ipady = 25;
        constraint.gridx = 0;
        constraint.gridy = 1;
        panel.add(usernameLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 2;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 1;
        panel.add(usernameTextField, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.ipadx = 50;
        constraint.ipady = 25;
        constraint.gridx = 0;
        constraint.gridy = 2;
        panel.add(passwordLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 2;
        constraint.ipady = 25;
        constraint.ipadx = 200;
        constraint.gridx = 1;
        constraint.gridy = 2;
        panel.add(passwordTextField, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.ipady = 25;
        constraint.ipadx = 100;
        constraint.gridx = 1;
        constraint.gridy = 3;
        panel.add(loginButton, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        constraint.ipady = 25;
        constraint.ipadx = 100;
        constraint.gridx = 2;
        constraint.gridy = 3;
        panel.add(signUpButton, constraint);

        add(panel);
    }
}
