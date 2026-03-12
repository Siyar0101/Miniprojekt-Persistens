package model;

/**
 * Model class representing a line item in an order.
 * 
 * This class represents a single product and quantity within an order,
 * and provides methods to calculate the subtotal for that line.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class OrderLine {

    private Product product;   
    private int quantity;
    private Order order;

    /**
     * Constructor initializing an OrderLine with product and quantity.
     * 
     * @param product the Product in this line
     * @param quantity the quantity of the product
     */
    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Gets the product in this order line.
     * 
     * @return the Product
     */
    public Product getProduct() {
        return product;       
    }

    /**
     * Gets the quantity of the product.
     * 
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the order this line belongs to.
     * 
     * @return the Order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Calculates the subtotal for this order line.
     * 
     * @return the product price multiplied by quantity
     */
    public double calculateSubtotal() {
        return product.getPrice() * quantity;
    }

}
