package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDisplay extends JPanel implements ActionListener {
    private final JButton artist, albums, tracks, movies, menu;
    private final DatabaseDisplay display;

    public AddDisplay(DatabaseDisplay display) {
        setLayout(new GridLayout(5, 1));

        this.display = display;

        artist = new JButton("Add Artist");
        albums = new JButton("Add Album");
        tracks = new JButton("Add Tracks");
        movies = new JButton("Add Movie");
        menu = new JButton("Return to Menu");

        artist.addActionListener(this);
        albums.addActionListener(this);
        tracks.addActionListener(this);
        movies.addActionListener(this);
        menu.addActionListener(this);

        add(artist);
        add(albums);
        add(tracks);
        add(movies);
        add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(artist)) {
            display.changeView(new ArtistAdd(display));
        } else if (e.getSource().equals(albums)) {
            display.changeView(new AlbumAdd(display));
        } else if (e.getSource().equals(tracks)) {
            display.changeView(new TrackAdd(display));
        } else if (e.getSource().equals(movies)) {
            display.changeView(new MovieAdd(display));
        } else if (e.getSource().equals(menu)) {
            display.changeView(new MainMenuDisplay(display));
        }
    }
}
