package dev.brianduffy.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResults extends JPanel implements ActionListener {
    private final DatabaseDisplay display;
    private final Object[] results;

    public SearchResults(DatabaseDisplay display, Object[] results) {
        super(new GridLayout(2, 1));

        this.display = display;
        this.results = results;

        JPanel scroll = new JPanel(new GridLayout(Math.max(results.length, 1), 1));
        JButton menu = new JButton("Return to Main Menu");

        for (Object o : results) {
            scroll.add(new JLabel(o.toString()));
        }
        if (results.length <= 0){
            scroll.add(new JLabel("No Results"));
        }

        menu.addActionListener(this);

        add(scroll);
        add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        display.changeView(new MainMenuDisplay(display));
    }
}
