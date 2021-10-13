package group1.cse3241.databaseinterface.schema;

public class Media {
    protected final String title, content_rating, length, creator_name;
    protected final MediaType type;
    protected final int year;

    public Media(String title, String content_rating, String length, int year, String creator_name, MediaType type) {
        this.title = title;
        this.content_rating = content_rating;
        this.length = length;
        this.year = year;
        this.creator_name = creator_name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Media{" +
                "title='" + title + '\'' +
                ", content_rating='" + content_rating + '\'' +
                ", length='" + length + '\'' +
                ", creator_name='" + creator_name + '\'' +
                ", type=" + type +
                ", year=" + year +
                '}';
    }

    public enum MediaType {
        ALBUM,
        MOVIE,
        AUDIO_BOOK
    }
}
