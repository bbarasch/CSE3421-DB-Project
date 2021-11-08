package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsDisplay extends JPanel implements ActionListener {
	private final JButton beforeYear, albumsChecked, popActor, popArtist, moviesChecked;
    private final JButton back;
    private final DatabaseDisplay display;

    public ReportsDisplay(DatabaseDisplay display) {
        super(new GridLayout(6, 1));

        this.display = display;
        
        beforeYear = new JButton("a.\tTracks by ARTIST released before YEAR");
        albumsChecked = new JButton("b.\tNumber of albums checked out by a single patron");
        popActor = new JButton("c.\tMost popular actor in the database");
        popArtist = new JButton("d.\tMost listened to artist in the database");
        moviesChecked = new JButton("e.\tPatron who has checked out the most videos");
        
        beforeYear.addActionListener(this);
        albumsChecked.addActionListener(this);
        popActor.addActionListener(this);
        popArtist.addActionListener(this);
        moviesChecked.addActionListener(this);
        
        add(beforeYear);
        add(albumsChecked);
        add(popActor);
        add(popArtist);
        add(moviesChecked);

        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource().equals(beforeYear)) {
            display.changeView(new ArtistYearForm(display));
        } else if (e.getSource().equals(albumsChecked)) {
            display.changeView(new AlbumsCheckedForm(display));
        } else if (e.getSource().equals(popActor)) {
            display.changeView(new PopActorDisplay(display));
        } else if (e.getSource().equals(popArtist)) {
            display.changeView(new PopArtistDisplay(display));
        } else if (e.getSource().equals(moviesChecked)) {
            display.changeView(new MostMoviesDisplay(display));
        } else if (e.getSource().equals(back)) {
        	display.changeView(new MainMenuDisplay(display));
        }
    }
}
