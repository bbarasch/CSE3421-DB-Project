package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuDisplay extends JPanel implements ActionListener {
    private final JButton search, addRecords, orderItems, editRecords, reports, debug;
    private final DatabaseDisplay display;

    public MainMenuDisplay(DatabaseDisplay display) {
        super(new GridLayout(6, 1));

        this.display = display;

        search = new JButton("a.\tSearch");
        addRecords = new JButton("b.\tAdd new records");
        orderItems = new JButton("c.\tOrder Items");
        editRecords = new JButton("d.\tEdit Records");
        reports = new JButton("e.\tUseful Reports");
        debug = new JButton("f.\tShow Everything");

        search.addActionListener(this);
        addRecords.addActionListener(this);
        orderItems.addActionListener(this);
        editRecords.addActionListener(this);
        reports.addActionListener(this);
        debug.addActionListener(this);

        add(search);
        add(addRecords);
        add(orderItems);
        add(editRecords);
        add(reports);
        add(debug);
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
            display.changeView(new EditDisplay(display));
        } else if (e.getSource().equals(reports)) {
            display.changeView(new ReportsDisplay(display));
        } else if (e.getSource().equals(debug)) {
            display.changeView(new DebugDisplay(display));
        }
    }
}