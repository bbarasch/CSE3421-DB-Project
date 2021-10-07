package dev.brianduffy.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        DatabaseDisplay frame = new DatabaseDisplay();
    }
}

class DatabaseDisplay extends JFrame {
    private final Set<Artist> artistSet;
    private final Set<Track> trackSet;

    public DatabaseDisplay() {
        super("Database Interface");

        artistSet = new HashSet<>();
        trackSet = new HashSet<>();

        artistSet.add(new Artist("Brian"));
        artistSet.add(new Artist("Brian"));
        artistSet.add(new Artist("Brian"));
        artistSet.add(new Artist("Connor"));
        artistSet.add(new Artist("Ben"));
        artistSet.add(new Artist("Steve"));

        changeView(new MainMenu(this));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void changeView(JPanel view) {
        getContentPane().removeAll();
        view.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        add(view);

        pack();
        repaint();
    }

    public Set<Artist> getArtistSet() {
        return artistSet;
    }

    public Set<Track> getTrackSet() {
        return trackSet;
    }
}

class MainMenu extends JPanel implements ActionListener {
    private final JButton search, addRecords, orderItems, editRecords, reports;
    private final DatabaseDisplay display;

    public MainMenu(DatabaseDisplay display) {
        super(new GridLayout(5, 1));

        this.display = display;

        search = new JButton("a.\tSearch");
        addRecords = new JButton("b.\tAdd new records");
        orderItems = new JButton("c.\tOrder Items");
        editRecords = new JButton("d.\tEdit Records");
        reports = new JButton("e.\tUseful Reports");

        search.addActionListener(this);
        addRecords.addActionListener(this);
        orderItems.addActionListener(this);
        editRecords.addActionListener(this);
        reports.addActionListener(this);

        add(search);
        add(addRecords);
        add(orderItems);
        add(editRecords);
        add(reports);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(search)) {
            display.changeView(new Search(display));
        } else if (e.getSource().equals(addRecords)) {
            System.out.println("Got Add Button");
        } else if (e.getSource().equals(orderItems)) {
            System.out.println("Got Order Button");
        } else if (e.getSource().equals(editRecords)) {
            System.out.println("Got Edit Button");
        } else if (e.getSource().equals(reports)) {
            System.out.println("Got Reports Button");
        }
    }
}

class Search extends JPanel implements ActionListener {
    private final JButton artist, tracks, menu;
    private final DatabaseDisplay display;

    public Search(DatabaseDisplay display) {
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
            display.changeView(new MainMenu(display));
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
            display.changeView(new MainMenu(display));
        }
    }
}

class Artist {
    private final UUID uuid;
    private final String name;

    public Artist(String name) {
        this.name = name;
        uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return name + " (" + uuid + ")";
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }
}

class Track {
    private String trackName;
    private int year;
    private Artist artist;

    public Track(int year, String trackName, Artist artist) {
        this.year = year;
        this.trackName = trackName;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return trackName + ": " + year + " by " + artist;
    }
}