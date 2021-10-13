package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.ContentCreator;
import group1.cse3241.databaseinterface.schema.Track;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class SearchDisplay extends JPanel implements ActionListener {
    private final JButton artist, tracks, menu;
    private final DatabaseDisplay display;

    public SearchDisplay(DatabaseDisplay display) {
        super(new GridLayout(3, 1));

        this.display = display;

        artist = new JButton("Search by Artist");
        tracks = new JButton("Search by Tracks");
        menu = new JButton("Return to Menu");

        artist.addActionListener(this);
        tracks.addActionListener(this);
        menu.addActionListener(this);

        add(artist);
        add(tracks);
        add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(artist)) {
            display.changeView(new ArtistSearch(display));
        } else if (e.getSource().equals(tracks)) {
            display.changeView(new TrackSearch(display));
        } else if (e.getSource().equals(menu)) {
            display.changeView(new MainMenuDisplay(display));
        }
    }
}
