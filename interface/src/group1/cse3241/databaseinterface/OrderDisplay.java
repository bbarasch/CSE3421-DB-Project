package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.Copy;
import group1.cse3241.databaseinterface.schema.Media;
import group1.cse3241.databaseinterface.schema.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class OrderDisplay extends JPanel implements ActionListener {
    private final JLabel warningMessage;
    private final JTextField priceEntry, dateEntry, orderEntry, copyEntry;
    private final JComboBox<String> titleEntry;
    private final JButton submit;
    private final DatabaseDisplay display;

    public OrderDisplay(DatabaseDisplay display) {
        setLayout(new GridLayout(12, 1));

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
        String[] creators = display.getMovieMap().keySet().toArray(new String[0]);
        titleEntry = new JComboBox<>(creators);
        add(titleEntry);

        submit = new JButton("Add Order");
        submit.addActionListener(this);
        add(submit);

        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String price = priceEntry.getText();
        String date = dateEntry.getText();
        String title = (String) titleEntry.getSelectedItem();
        int order_number, num_copies;
        try {
            order_number = Integer.parseInt(orderEntry.getText());
            num_copies = Integer.parseInt(copyEntry.getText());
        } catch (NumberFormatException err) {
            warningMessage.setText("Order Number and Number of Copies must be integers");
            display.pack();
            return;
        }
        if (display.getOrderMap().containsKey(order_number)) {
            warningMessage.setText("Order number already exists!");
            display.pack();
            return;
        }
        Order order = new Order(price, date, title, order_number, num_copies);
        display.getOrderMap().put(order_number, order);
        for (int i = 0; i < num_copies; i++) {
            int uuid = (int) UUID.randomUUID().getLeastSignificantBits();
            Copy copy = new Copy(uuid, order_number, title, Copy.CopyStatus.SHIPPING, Media.MediaType.MOVIE);
            display.getCopyMap().put(uuid, copy);
        }
        display.changeView(new MainMenuDisplay(display));
    }
}
