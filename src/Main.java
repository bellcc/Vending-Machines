import java.awt.*;

import javax.swing.*;

import screens.*;

public class Main
{
    public static void main(String [] args)
    {
        JFrame frame = new JFrame("Vending Machines");
        AdminPanel adminPanel = new AdminPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(adminPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
