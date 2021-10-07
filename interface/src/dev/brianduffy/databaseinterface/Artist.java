package dev.brianduffy.databaseinterface;

import java.util.UUID;

public class Artist {
    private final UUID uuid;
    private final String name;

    public Artist(String name) {
        this.name = name;
        uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return name + " (" + uuid + ")";
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }
}
