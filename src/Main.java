import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import screens.*;

public class Main
{
    public static void main(String [] args) throws ClassNotFoundException, SQLException
    {
        JFrame frame = new JFrame("Vending Machines");
        //BuyerProfilePanel profilePanel = new BuyerProfilePanel();
        //LoginPanel loginPanel = new LoginPanel();
        //AdminPanel adminPanel = new AdminPanel();
        SupplierPanel supplierPanel = new SupplierPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);

        frame.add(supplierPanel, BorderLayout.CENTER);
        //frame.add(loginPanel, BorderLayout.CENTER);
        //frame.add(profilePanel, BorderLayout.CENTER);
        //frame.add(adminPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
