package group1.cse3241.databaseinterface.schema;

public class Copy {
    private final int id_num, order_number;
    private final String media_name;
    private final CopyStatus status;
    private final Media.MediaType type;

    public Copy(int id_num, int order_number, String media_name, CopyStatus status, Media.MediaType type) {
        this.id_num = id_num;
        this.order_number = order_number;
        this.media_name = media_name;
        this.status = status;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Copy{" +
                "id_num=" + id_num +
                ", order_number=" + order_number +
                ", media_name='" + media_name + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    public enum CopyStatus {
        AVAILABLE,
        CHECKED_OUT,
        SHIPPING,
        LOST
    }
}
