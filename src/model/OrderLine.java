package model;

import java.time.LocalDate;

public class OrderLine {
    private Product p;
    private int quantity;
    private Order order;

    public OrderLine(Product p, int quantity) {
        this.p = p;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return p;
    }

    public int getQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }

    public double calculateSubtotal() {
        
        return p.getPrice(LocalDate.now()) * quantity;
    }
}

