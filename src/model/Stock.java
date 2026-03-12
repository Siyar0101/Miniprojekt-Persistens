package model;

/**
 * Model class representing stock of a product in a warehouse.
 * 
 * This class tracks the quantity of a specific product available in a warehouse.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Stock {
    private Product product;
    private Warehouse warehouse;
    private int availableQty;

    /**
     * Constructor initializing Stock with product, warehouse, and quantity.
     * 
     * @param product the Product in stock
     * @param warehouse the Warehouse where the product is stored
     * @param availableQty the available quantity
     */
    public Stock(Product product, Warehouse warehouse, int availableQty) {
        this.product = product;
        this.warehouse = warehouse;
        this.availableQty = availableQty;
    }

    /**
     * Gets the product in this stock.
     * 
     * @return the Product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Gets the warehouse where the product is stored.
     * 
     * @return the Warehouse
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Gets the available quantity.
     * 
     * @return the available quantity
     */
    public int getAvailableQty() {
        return availableQty;
    }

    /**
     * Sets the available quantity.
     * 
     * @param qty the quantity to set
     */
    public void setAvailableQty(int qty) {
        this.availableQty = qty;
    }
}
