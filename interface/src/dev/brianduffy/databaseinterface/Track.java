package dev.brianduffy.databaseinterface;

import java.util.UUID;

public class Track {
    private final UUID uuid;
    private final String trackName;
    private final int year;
    private final Artist artist;

    public Track(int year, String trackName, Artist artist) {
        this.year = year;
        this.trackName = trackName;
        this.artist = artist;
        uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return trackName + ": " + year + " by " + artist + " (" + uuid + ")";
    }

    public String getTrackName() {
        return trackName;
    }
}
