package dev.brianduffy.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        DatabaseDisplay frame = new DatabaseDisplay();
    }


}

class DatabaseDisplay extends JFrame {
    private JPanel child;

    public DatabaseDisplay() {
        super("Database Interface");

        child = new MainMenu();
        add(child);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MainMenu extends JPanel implements ActionListener {
    private JButton search, addRecords, orderItems, editRecords, reports;

    public MainMenu() {
        super(new GridLayout(5, 1));

        search = new JButton("a.\tSearch");
        addRecords = new JButton("b.\tAdd new records");
        orderItems = new JButton("c.\tOrder Items");
        editRecords = new JButton("d.\tEdit Records");
        reports = new JButton("e.\tUseful Reports");

        search.addActionListener(this);

        add(search);
        add(addRecords);
        add(orderItems);
        add(editRecords);
        add(reports);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(search)) {
            System.out.println("Got Search Button");
        }
    }
}