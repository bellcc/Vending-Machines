import java.awt.*;
import java.net.URL;

import javax.swing.*;

import screens.BuyerProfilePanel;
import screens.LoginPanel;

public class Main
{
    public static void main(String [] args)
    {
        JFrame frame = new JFrame("Vending Machines");
        LoginPanel loginPanel = new LoginPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        frame.add(loginPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
