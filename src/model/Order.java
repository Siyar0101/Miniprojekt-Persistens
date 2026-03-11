package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer c;
    private List<OrderLine> orderLines = new ArrayList<>();
    private int orderNo;
    private LocalDateTime date;
    private double amount;
    private double discountGiven;

    public Order() {
        this.date = LocalDateTime.now();
    }

    public void addCustomer(Customer c) {
        this.c = c;
    }

    public void addOrderLine(OrderLine ol) {
        orderLines.add(ol);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderLine ol : orderLines) {
            total += ol.calculateSubtotal();
        }
        amount = total - discountGiven;
        return amount;
    }
}
