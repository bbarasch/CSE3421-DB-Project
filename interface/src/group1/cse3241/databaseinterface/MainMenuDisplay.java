package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuDisplay extends JPanel implements ActionListener {
    private final JButton search, addRecords, orderItems, editRecords, reports;
    private final DatabaseDisplay display;

    public MainMenuDisplay(DatabaseDisplay display) {
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
            display.changeView(new SearchDisplay(display));
        } else if (e.getSource().equals(addRecords)) {
            display.changeView(new AddDisplay(display));
        } else if (e.getSource().equals(orderItems)) {
            display.changeView(new OrderDisplay(display));
        } else if (e.getSource().equals(editRecords)) {
            System.out.println("Got Edit Button");
        } else if (e.getSource().equals(reports)) {
            System.out.println("Got Reports Button");
        }
    }
}