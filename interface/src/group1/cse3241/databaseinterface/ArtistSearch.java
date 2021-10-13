package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.ContentCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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
        Map<String, ContentCreator> artistMap = display.getCreatorMap();
        Object[] results = artistMap.entrySet().stream().filter(artist ->
                artist.getKey().equalsIgnoreCase(term)).toArray();
        display.changeView(new SearchResults(display, results));
    }
}