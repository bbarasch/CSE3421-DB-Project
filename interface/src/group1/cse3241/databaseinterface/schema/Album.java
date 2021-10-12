package group1.cse3241.databaseinterface.schema;

public class Album extends Media {
    protected String record_label, music_genre;

    public Album(String title, String content_rating, String length, int year, String record_label, String music_genre, String creator_name) {
        super(title, content_rating, length, year, creator_name);
        this.record_label = record_label;
        this.music_genre = music_genre;
    }

    @Override
    public String toString() {
        return "Album{" +
                "record_label='" + record_label + '\'' +
                ", music_genre='" + music_genre + '\'' +
                ", title='" + title + '\'' +
                ", content_rating='" + content_rating + '\'' +
                ", length='" + length + '\'' +
                ", year=" + year +
                '}';
    }
}
