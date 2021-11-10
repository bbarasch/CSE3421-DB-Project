package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.ContentCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class ArtistSearch extends JPanel implements ActionListener {
    private final JButton back;
    private final JTextField entry;
    private final DatabaseDisplay display;

    public ArtistSearch(DatabaseDisplay display) {
        super(new GridLayout(3, 1));

        this.display = display;

        entry = new JTextField();
        JButton search = new JButton("Search");

        search.addActionListener(this);

        add(entry);
        add(search);

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
        String term = entry.getText();
        String sqlStat = "SELECT C.Name, T.Type FROM CONTENT_CREATOR as C, CREATOR_TYPE as T"
                            + " WHERE T.Creator_name = C.Name"
                            + " AND C.Name = '" + term + "';";
        JTable result = Main.sqlQuery(Main.conn, sqlStat);
        display.changeView(new SearchResults(display, result));
    }
}