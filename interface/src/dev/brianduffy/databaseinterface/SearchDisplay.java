package dev.brianduffy.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.function.Predicate;

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

        } else if (e.getSource().equals(menu)) {
            display.changeView(new MainMenuDisplay(display));
        }
    }

    private static class ArtistSearch extends JPanel implements ActionListener {
        private final JButton search;
        private final JTextField entry;
        private final DatabaseDisplay display;

        public ArtistSearch(DatabaseDisplay display) {
            super(new GridLayout(2, 1));

            this.display = display;

            entry = new JTextField();
            search = new JButton("Search");

            search.addActionListener(this);

            add(entry);
            add(search);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String term = entry.getText();
            Set<Artist> artistSet = display.getArtistSet();
            Object[] results = artistSet.stream().filter(new Predicate<Artist>() {
                @Override
                public boolean test(Artist artist) {
                    return artist.getName().equalsIgnoreCase(term);
                }
            }).toArray();
            display.changeView(new SearchResults(display, results));
        }
    }

    private static class SearchResults extends JPanel implements ActionListener {
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
}
