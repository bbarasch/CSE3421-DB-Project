package group1.cse3241.databaseinterface.schema;

import java.util.Arrays;

public class Movie extends Media {
    private final String movie_genre, definition;
    private final String[] starring;

    public Movie(String title, String content_rating, String length, int year, String creator_name, String movie_genre, String definition, String[] starring) {
        super(title, content_rating, length, year, creator_name, MediaType.MOVIE);
        this.movie_genre = movie_genre;
        this.definition = definition;
        this.starring = starring;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", content_rating='" + content_rating + '\'' +
                ", length='" + length + '\'' +
                ", creator_name='" + creator_name + '\'' +
                ", type=" + type +
                ", year=" + year +
                ", movie_genre='" + movie_genre + '\'' +
                ", definition='" + definition + '\'' +
                ", starring=" + Arrays.toString(starring) +
                '}';
    }
}
