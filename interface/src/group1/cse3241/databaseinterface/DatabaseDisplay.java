package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.Album;
import group1.cse3241.databaseinterface.schema.ContentCreator;
import group1.cse3241.databaseinterface.schema.Track;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseDisplay extends JFrame {
    private final Map<String, ContentCreator> artistMap;
    private final Map<String, Album> albumMap;
    private final Map<String, Track> trackMap;

    public DatabaseDisplay() {
        super("Database Interface");

        artistMap = new HashMap<>();
        albumMap = new HashMap<>();
        trackMap = new HashMap<>();

        // TODO: Test data, remove
        artistMap.put("Brian", new ContentCreator("Brian", new ContentCreator.ArtistType[]{ContentCreator.ArtistType.AUTHOR}));
        artistMap.put("Connor", new ContentCreator("Connor", new ContentCreator.ArtistType[]{ContentCreator.ArtistType.AUTHOR}));
        artistMap.put("Ben", new ContentCreator("Ben", new ContentCreator.ArtistType[]{ContentCreator.ArtistType.AUTHOR}));
        artistMap.put("Steve", new ContentCreator("Steve", new ContentCreator.ArtistType[]{ContentCreator.ArtistType.AUTHOR}));

        trackMap.put("Hello", new Track("Hello", "1h", "Album1"));
        trackMap.put("World", new Track("World", "1h5m", "Album2"));
        trackMap.put("Lalala", new Track("Lalala", "1m54s", "Album3"));
        trackMap.put("Placeholder", new Track("Placeholder", "1d4h2m33s", "Album4"));
        trackMap.put("Testing", new Track("Testing", "0m", "Album5"));

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

    public Map<String, ContentCreator> getArtistMap() {
        return artistMap;
    }

    public Map<String, Album> getAlbumMap() {
        return albumMap;
    }

    public Map<String, Track> getTrackMap() {
        return trackMap;
    }
}
