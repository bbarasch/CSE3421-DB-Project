package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderDisplay extends JPanel implements ActionListener {
    private final DatabaseDisplay display;

    public OrderDisplay(DatabaseDisplay display) {
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
