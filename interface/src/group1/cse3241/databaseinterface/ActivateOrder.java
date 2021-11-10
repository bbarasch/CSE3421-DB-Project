package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.Copy;
import group1.cse3241.databaseinterface.schema.Media;
import group1.cse3241.databaseinterface.schema.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivateOrder extends JPanel implements ActionListener {
    private final JComboBox<String> titleEntry;
    private final JButton submit, back;
    private final DatabaseDisplay display;

    public ActivateOrder(DatabaseDisplay display) {
        setLayout(new GridLayout(3, 1));

        add(new JLabel("Order Number:"));
        String sqlQuery = "SELECT Tracking_number FROM DELIVERY_ORDER;";
        JTable table = Main.sqlQuery(Main.conn, sqlQuery);
        String[] movies = new String[table.getRowCount()];
        for (int i = 0; i < table.getRowCount(); i++) {
            movies[i] = (String) table.getValueAt(i, 0);
        }
        titleEntry = new JComboBox<>(movies);
        add(titleEntry);

        submit = new JButton("Activate Order");
        submit.addActionListener(this);
        add(submit);

        this.display = display;

        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
            display.changeView(new MainMenuDisplay(display));
            return;
        }
        int tracking = Integer.parseInt((String) titleEntry.getSelectedItem());

        String activate = "UPDATE COPY SET Status = 'Available' WHERE Tracking_num = " + tracking + ";";
        Main.sqlQuery(Main.conn, activate);

        display.changeView(new MainMenuDisplay(display));
    }
}
