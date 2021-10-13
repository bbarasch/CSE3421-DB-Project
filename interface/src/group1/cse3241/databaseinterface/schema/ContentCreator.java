package group1.cse3241.databaseinterface.schema;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class ContentCreator {
    private final UUID uuid;
    private final ArtistType types[];
    private final String name;

    public ContentCreator(String name, ArtistType types[]) {
        this.name = name;
        this.types = types;
        uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "ContentCreator{" +
                "uuid=" + uuid +
                ", types=" + Arrays.toString(types) +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public enum ArtistType {
        AUTHOR,
        ACTOR,
        DIRECTOR
    }
}
