package group1.cse3241.databaseinterface.schema;

public class Order {
    private final String price, date_of_arrival, media_title;
    private final int order_number, num_copies;

    public Order(String price, String date_of_arrival, String media_title, int order_number, int num_copies) {
        this.price = price;
        this.date_of_arrival = date_of_arrival;
        this.media_title = media_title;
        this.order_number = order_number;
        this.num_copies = num_copies;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price='" + price + '\'' +
                ", date_of_arrival='" + date_of_arrival + '\'' +
                ", media_title='" + media_title + '\'' +
                ", order_number=" + order_number +
                ", num_copies=" + num_copies +
                '}';
    }
}
