package group1.cse3241.databaseinterface.schema;

import java.util.Arrays;

public class ContentCreator {
    private final ArtistType[] types;
    private final String name;

    public ContentCreator(String name, ArtistType[] types) {
        this.name = name;
        this.types = types;
    }

    @Override
    public String toString() {
        return "ContentCreator{" +
                ", types=" + Arrays.toString(types) +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public enum ArtistType {
        AUTHOR,
        ACTOR,
        DIRECTOR,
        MUSICIAN,
        NARRATOR
    }
}
