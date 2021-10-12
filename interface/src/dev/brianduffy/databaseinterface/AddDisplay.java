package dev.brianduffy.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDisplay extends JPanel implements ActionListener {
    private final JButton artist, tracks, menu;
    private final DatabaseDisplay display;

    public AddDisplay(DatabaseDisplay display) {
        super(new GridLayout(3, 1));

        this.display = display;

        artist = new JButton("Add Artist");
        tracks = new JButton("add Tracks");
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
            display.changeView(new ArtistAdd(display));
        } else if (e.getSource().equals(tracks)) {
            display.changeView(new TrackAdd(display));
        } else if (e.getSource().equals(menu)) {
            display.changeView(new MainMenuDisplay(display));
        }
    }

    private static class ArtistAdd extends JPanel implements ActionListener {
        private final JTextField entry;
        private final DatabaseDisplay display;

        public ArtistAdd(DatabaseDisplay display) {
            super(new GridLayout(3, 1));

            this.display = display;

            add(new JLabel("Artist Name:"));

            entry = new PlaceholderTestField("Artist Name");
            add(entry);

            JButton search = new JButton("Add artist");
            search.addActionListener(this);
            add(search);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = entry.getText();
            Artist artist = new Artist(name);
            display.getArtistSet().add(artist);
            display.changeView(new MainMenuDisplay(display));
        }
    }

    private static class TrackAdd extends JPanel implements ActionListener {
        private final JLabel warningMessage;
        private final JTextField trackEntry, yearEntry, artistEntry;
        private final DatabaseDisplay display;

        public TrackAdd(DatabaseDisplay display) {
            super();
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            this.display = display;
            warningMessage = new JLabel("");
            warningMessage.setForeground(Color.RED);
            add(warningMessage);

            add(new JLabel("Track Name:"));
            trackEntry = new PlaceholderTestField("Track Name");
            add(trackEntry);

            add(new JLabel("Year Released:"));
            yearEntry = new PlaceholderTestField("Year Released");
            add(yearEntry);

            add(new JLabel("Artist:"));
            artistEntry = new PlaceholderTestField("Artist");
            add(artistEntry);

            JButton addButton = new JButton("Add Track");
            addButton.addActionListener(this);
            add(addButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int year;
            try {
                year = Integer.parseInt(yearEntry.getText());
            } catch (NumberFormatException err) {
                warningMessage.setText("Year must be a positive integer!");
                display.pack();
                return;
            }
            String title = trackEntry.getText();
            String artist = artistEntry.getText();
            Track track = new Track(year, title, new Artist(artist));
            display.getTrackSet().add(track);
            display.changeView(new MainMenuDisplay(display));
        }
    }
}
