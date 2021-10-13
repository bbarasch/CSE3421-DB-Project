package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsDisplay extends JPanel implements ActionListener {
    private final JButton back;
    private final DatabaseDisplay display;

    public ReportsDisplay(DatabaseDisplay display) {
        super(new GridLayout(6, 1));

        this.display = display;

        add(new JButton("a.\tTracks by ARTIST released before YEAR"));
        add(new JButton("b.\tNumber of albums checked out by a single patron"));
        add(new JButton("c.\tMost popular actor in the database"));
        add(new JButton("d.\tMost listened to artist in the database"));
        add(new JButton("e.\tPatron who has checked out the most videos"));

        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        display.changeView(new MainMenuDisplay(display));
    }
}
