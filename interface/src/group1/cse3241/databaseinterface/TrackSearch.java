package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.Track;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TrackSearch extends JPanel implements ActionListener {
    private final JButton back;
    private final JTextField entry;
    private final DatabaseDisplay display;

    public TrackSearch(DatabaseDisplay display) {
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
        Map<String, Track> trackMap = display.getTrackMap();
        Object[] results = trackMap.entrySet().stream().filter(track ->
                track.getKey().equalsIgnoreCase(term)).toArray();
        display.changeView(new SearchResults(display, results));
    }
}