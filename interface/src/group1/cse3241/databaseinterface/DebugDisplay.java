package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DebugDisplay extends JPanel implements ActionListener {
    private final JButton creator, album, track, order, movie, copy, back;
    private final DatabaseDisplay display;

    public DebugDisplay(DatabaseDisplay display) {
        super(new GridLayout(7, 1));

        this.display = display;

        creator = new JButton("Creators");
        creator.addActionListener(this);
        add(creator);

        album = new JButton("Album");
        album.addActionListener(this);
        add(album);

        track = new JButton("Track");
        track.addActionListener(this);
        add(track);

        order = new JButton("Order");
        order.addActionListener(this);
        add(order);

        movie = new JButton("Movie");
        movie.addActionListener(this);
        add(movie);

        copy = new JButton("Copy");
        copy.addActionListener(this);
        add(copy);

        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(creator)) {
            display.changeView(new SearchResults(display, display.getCreatorMap().values().toArray(new ContentCreator[0])));
        } else if (e.getSource().equals(album)) {
            display.changeView(new SearchResults(display, display.getAlbumMap().values().toArray(new Album[0])));
        } else if (e.getSource().equals(track)) {
            display.changeView(new SearchResults(display, display.getTrackMap().values().toArray(new Track[0])));
        } else if (e.getSource().equals(order)) {
            display.changeView(new SearchResults(display, display.getOrderMap().values().toArray(new Order[0])));
        } else if (e.getSource().equals(movie)) {
            display.changeView(new SearchResults(display, display.getMovieMap().values().toArray(new Movie[0])));
        } else if (e.getSource().equals(copy)) {
            display.changeView(new SearchResults(display, display.getCopyMap().values().toArray(new Copy[0])));
        } else if (e.getSource().equals(back)) {
            display.changeView(new MainMenuDisplay(display));
        }

    }
}
