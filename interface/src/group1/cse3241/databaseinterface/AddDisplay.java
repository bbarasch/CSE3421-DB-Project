package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.Album;
import group1.cse3241.databaseinterface.schema.ContentCreator;
import group1.cse3241.databaseinterface.schema.Track;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDisplay extends JPanel implements ActionListener {
    private final JButton artist, albums, tracks, menu;
    private final DatabaseDisplay display;

    public AddDisplay(DatabaseDisplay display) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.display = display;

        artist = new JButton("Add Artist");
        albums = new JButton("Add Album");
        tracks = new JButton("Add Tracks");
        menu = new JButton("Return to Menu");

        artist.addActionListener(this);
        albums.addActionListener(this);
        tracks.addActionListener(this);
        menu.addActionListener(this);

        add(artist);
        add(albums);
        add(tracks);
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
        } else if (e.getSource().equals(menu)) {
            display.changeView(new MainMenuDisplay(display));
        }
    }

    private static class AlbumAdd extends JPanel implements ActionListener {
        private final JTextField title, content_rating, length, year, record_label, music_genre;
        private final JComboBox<String> creator_name;
        private final JLabel warningMessage;
        private final DatabaseDisplay display;

        public AlbumAdd(DatabaseDisplay display) {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            this.display = display;

            warningMessage = new JLabel("");
            warningMessage.setForeground(Color.RED);
            add(warningMessage);

            add(new JLabel("Album Title:"));
            title = new PlaceholderTestField("Album Title");
            add(title);

            add(new JLabel("Content Rating:"));
            content_rating = new PlaceholderTestField("Content Rating");
            add(content_rating);

            add(new JLabel("Album Length:"));
            length = new PlaceholderTestField("Album Length");
            add(length);

            add(new JLabel("Year released:"));
            year = new PlaceholderTestField("Year released");
            add(year);

            add(new JLabel("Record Label:"));
            record_label = new PlaceholderTestField("Record Label");
            add(record_label);

            add(new JLabel("Music Genre:"));
            music_genre = new PlaceholderTestField("Music Genre");
            add(music_genre);

            add(new JLabel("Artist:"));
            String[] creators = display.getArtistMap().keySet().toArray(new String[0]);
            creator_name = new JComboBox<>(creators);
            add(creator_name);

            JButton submit = new JButton("Add Album");
            submit.addActionListener(this);
            add(submit);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int year; // = this.year.getText();
            try {
                year = Integer.parseInt(this.year.getText());
            } catch (NumberFormatException err) {
                warningMessage.setText("Year must be an integer");
                display.pack();
                return;
            }

            String title = this.title.getText();
            if (display.getAlbumMap().containsKey(title)) {
                warningMessage.setText("An album with that title already exists!");
                return;
            }

            String content_rating = this.content_rating.getText();
            String length = this.length.getText();
            String record_label = this.record_label.getText();
            String music_genre = this.music_genre.getText();
            String creator_name = (String) this.creator_name.getSelectedItem();
            Album album = new Album(title, content_rating, length, year, record_label, music_genre, creator_name);
            display.getAlbumMap().put(title, album);
            display.changeView(new MainMenuDisplay(display));
        }
    }

    private static class ArtistAdd extends JPanel implements ActionListener {
        private final JTextField entry;
        private final DatabaseDisplay display;

        public ArtistAdd(DatabaseDisplay display) {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

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
            ContentCreator artist = new ContentCreator(name, new ContentCreator.ArtistType[]{ContentCreator.ArtistType.AUTHOR});
            display.getArtistMap().put(name, artist);
            display.changeView(new MainMenuDisplay(display));
        }
    }

    private static class TrackAdd extends JPanel implements ActionListener {
        private final JLabel warningMessage;
        private final JTextField trackEntry, lengthEntry;
        private final JComboBox<String> albumEntry;
        private final DatabaseDisplay display;

        public TrackAdd(DatabaseDisplay display) {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            this.display = display;

            warningMessage = new JLabel("");
            warningMessage.setForeground(Color.RED);
            add(warningMessage);

            add(new JLabel("Track Name:"));
            trackEntry = new PlaceholderTestField("Track Name");
            add(trackEntry);

            add(new JLabel("Track length:"));
            lengthEntry = new PlaceholderTestField("Track Length");
            add(lengthEntry);

            add(new JLabel("Album:"));
            String[] albums = display.getAlbumMap().keySet().toArray(new String[0]);
            albumEntry = new JComboBox<>(albums);
            add(albumEntry);

            JButton addButton = new JButton("Add Track");
            addButton.addActionListener(this);
            add(addButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String length = lengthEntry.getText();
            String title = trackEntry.getText();
            String album = (String) albumEntry.getSelectedItem();
            Track track = new Track(title, length, album);
            display.getTrackMap().put(title, track);
            display.changeView(new MainMenuDisplay(display));
        }
    }
}
