package dev.brianduffy.databaseinterface;

public class Track {
    private final String trackName;
    private final int year;
    private final Artist artist;

    public Track(int year, String trackName, Artist artist) {
        this.year = year;
        this.trackName = trackName;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return trackName + ": " + year + " by " + artist;
    }
}
