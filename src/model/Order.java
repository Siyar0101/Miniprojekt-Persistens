package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;              // ✔ store the actual customer object
    private List<OrderLine> orderLines = new ArrayList<>();
    private int orderNo;
    private LocalDateTime date;
    private double amount;
    private double discountGiven;
    private int customerId;                 // ✔ store the FK for DB insert

    public Order() {
        this.date = LocalDateTime.now();
    }

    // ✔ FIXED: store BOTH the object and the ID
    public void addCustomer(Customer c) {
        this.customer = c;
        this.customerId = c.getId();
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

    // --- getters

    public Customer getCustomer() {
        return customer;                    // ✔ now returns the actual customer
    }

    public int getCustomerId() {
        return customerId;                  // ✔ used by OrderDB
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscountGiven() {
        return discountGiven;
    }

    // --- setters

    public void setCustomer(Customer c) {
        this.customer = c;
        this.customerId = c.getId();        // ✔ keep ID in sync
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDiscountGiven(double discountGiven) {
        this.discountGiven = discountGiven;
    }
}
