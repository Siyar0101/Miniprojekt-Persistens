import java.time.LocalDate;

public class Price {
    private double price;
    private LocalDate date;

    public Price(double price, LocalDate date) {
        this.price = price;
        this.date = date;
    }

    public double getPrice() { return price; }
    public LocalDate getDate() { return date; }
}
