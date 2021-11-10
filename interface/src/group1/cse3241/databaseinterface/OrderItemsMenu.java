package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderItemsMenu extends JPanel implements ActionListener {
    private final JButton order, activate, exit;
    private final DatabaseDisplay display;

    public OrderItemsMenu(DatabaseDisplay display) {
        super(new GridLayout(3, 1));

        this.display = display;

        order = new JButton("a.\tOrder new Movies");
        activate = new JButton("b.\tActivate existing order");
        exit = new JButton("c.\tReturn to Main Menu");

        order.addActionListener(this);
        activate.addActionListener(this);
        exit.addActionListener(this);

        add(order);
        add(activate);
        add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(order)) {
            display.changeView(new OrderDisplay(display));
        } else if (e.getSource().equals(activate)) {
            display.changeView(new ActivateOrder(display));
        } else if (e.getSource().equals(exit)) {
            display.changeView(new MainMenuDisplay(display));
        }
    }
}