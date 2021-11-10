package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResults extends JPanel implements ActionListener {
    private final DatabaseDisplay display;

    public SearchResults(DatabaseDisplay display, JTable results) {
        super(new GridLayout(2, 1));

        this.display = display;

        JButton menu = new JButton("Return to Main Menu");

        menu.addActionListener(this);

        add(results);
        add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        display.changeView(new MainMenuDisplay(display));
    }
}
