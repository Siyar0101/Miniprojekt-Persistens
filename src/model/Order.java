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

    // --- getters 
    
    public Customer getCustomer() {
        return c;
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
        this.c = c;
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
