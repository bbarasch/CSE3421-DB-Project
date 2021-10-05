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

        child = new MainMenu(this);
        add(child);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void changeView(JPanel view) {
        getContentPane().removeAll();
        child = view;
        add(child);

        pack();
        repaint();
    }
}

class MainMenu extends JPanel implements ActionListener {
    private final JButton search, addRecords, orderItems, editRecords, reports;
    private final DatabaseDisplay display;

    public MainMenu(DatabaseDisplay display) {
        super(new GridLayout(5, 1));

        this.display = display;

        search = new JButton("a.\tSearch");
        addRecords = new JButton("b.\tAdd new records");
        orderItems = new JButton("c.\tOrder Items");
        editRecords = new JButton("d.\tEdit Records");
        reports = new JButton("e.\tUseful Reports");

        search.addActionListener(this);
        addRecords.addActionListener(this);
        orderItems.addActionListener(this);
        editRecords.addActionListener(this);
        reports.addActionListener(this);

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
            display.changeView(new Search());
        } else if (e.getSource().equals(addRecords)) {
            System.out.println("Got Add Button");
        } else if (e.getSource().equals(orderItems)) {
            System.out.println("Got Order Button");
        } else if (e.getSource().equals(editRecords)) {
            System.out.println("Got Edit Button");
        } else if (e.getSource().equals(reports)) {
            System.out.println("Got Reports Button");
        }
    }
}

class Search extends JPanel {
    public Search() {
        super(new GridLayout(1, 1));
        
        add(new JButton("Hello World"));
    }
}
