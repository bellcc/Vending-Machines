import java.awt.*;
import javax.swing.*;

import screens.*;

public class Main
{
    public static void main(String [] args)
    {
        JFrame frame = new JFrame("Vending Machines");
        BuyerProfilePanel profilePanel = new BuyerProfilePanel();
//        LoginPanel loginPanel = new LoginPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);

//        frame.add(loginPanel, BorderLayout.CENTER);
        frame.add(profilePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
