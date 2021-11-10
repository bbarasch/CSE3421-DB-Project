package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class OrderDisplay extends JPanel implements ActionListener {
    private final JLabel warningMessage;
    private final JTextField priceEntry, dateEntry, orderEntry, copyEntry;
    private final JComboBox<String> titleEntry;
    private final JButton submit, back;
    private final DatabaseDisplay display;

    public OrderDisplay(DatabaseDisplay display) {
        setLayout(new GridLayout(13, 1));

        warningMessage = new JLabel("");
        warningMessage.setForeground(Color.RED);
        add(warningMessage);

        add(new JLabel("Price:"));
        priceEntry = new PlaceholderTextField("Price");
        add(priceEntry);

        add(new JLabel("Date of Arrival:"));
        dateEntry = new PlaceholderTextField("Date of Arrival");
        add(dateEntry);

        add(new JLabel("Order tracking number:"));
        orderEntry = new PlaceholderTextField("Tracking Number");
        add(orderEntry);

        add(new JLabel("Number of Copies:"));
        copyEntry = new PlaceholderTextField("Copies");
        add(copyEntry);

        add(new JLabel("Movie Title:"));
        String sqlQuery = "SELECT Title FROM MOVIE;";
        JTable table = Main.sqlQuery(Main.conn, sqlQuery);
        String[] movies = new String[table.getRowCount()];
        for (int i = 0; i < table.getRowCount(); i++) {
            movies[i] = (String) table.getValueAt(i, 0);
        }
        titleEntry = new JComboBox<>(movies);
        add(titleEntry);

        submit = new JButton("Add Order");
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
        double price;
        String date = dateEntry.getText();
        String title = (String) titleEntry.getSelectedItem();
        int order_number, num_copies;
        try {
            price = Double.parseDouble(priceEntry.getText());
            order_number = Integer.parseInt(orderEntry.getText());
            num_copies = Integer.parseInt(copyEntry.getText());
        } catch (NumberFormatException err) {
            warningMessage.setText("Order Number and Number of Copies must be integers");
            display.pack();
            return;
        }

        String sqlQuery = "INSERT INTO DELIVERY_ORDER (Tracking_number, Num_copies, Price, Date_of_arrival, Media_title, Media_type) VALUES("
                        + order_number + ", " + num_copies + ", " + price + ", " + date + ", '"
                        + title + "', 'Movie');";

        Main.sqlQuery(Main.conn, sqlQuery);

        for (int i = 0; i < num_copies; i++) {
            int id = (int) UUID.randomUUID().getLeastSignificantBits() & 0x7FFFFFFF;
            String copyQuery = "INSERT INTO COPY (Id_copy, Type, Status, Media_title, Tracking_num, Media_type) VALUES ("
                                + id + ", 'Physical', 'Transit', '" + title + "', " + order_number + ", 'Movie');";
            Main.sqlQuery(Main.conn, copyQuery);
        }
        display.changeView(new MainMenuDisplay(display));
    }
}
