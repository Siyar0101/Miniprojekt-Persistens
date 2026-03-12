package model;

import java.time.LocalDate;

public class OrderLine {

    private Product product;   // ✔ renamed for clarity and consistency
    private int quantity;
    private Order order;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;        // ✔ now matches the field name
    }

    public int getQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }

    public double calculateSubtotal() {
        return product.getPrice() * quantity;
    }

}
