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

        // TODO: Test data, remove
        artistSet.add(new Artist("Brian"));
        artistSet.add(new Artist("Brian"));
        artistSet.add(new Artist("Brian"));
        artistSet.add(new Artist("Connor"));
        artistSet.add(new Artist("Ben"));
        artistSet.add(new Artist("Steve"));

        trackSet.add(new Track(2000, "Hello", new Artist("Brian")));
        trackSet.add(new Track(2300, "World", new Artist("Nope")));
        trackSet.add(new Track(4696, "Lalala", new Artist("Artist")));
        trackSet.add(new Track(69420, "Placeholder", new Artist("Blah")));
        trackSet.add(new Track(-1, "Testing", new Artist("Sarah")));

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
