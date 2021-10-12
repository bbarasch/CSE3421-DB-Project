package group1.cse3241.databaseinterface.schema;

public class Track {
    private final String title, length, album_title;

    public Track(String title, String length, String album_title) {
        this.title = title;
        this.length = length;
        this.album_title = album_title;
    }

    @Override
    public String toString() {
        return "Track{" +
                "title='" + title + '\'' +
                ", length='" + length + '\'' +
                ", album_title='" + album_title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getLength() {
        return length;
    }

    public String getAlbum_title() {
        return album_title;
    }
}
