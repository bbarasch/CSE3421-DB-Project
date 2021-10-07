package dev.brianduffy.databaseinterface;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class DatabaseDisplay extends JFrame {
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

        changeView(new MainMenuDisplay(this));

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
