package model;

import java.time.LocalDate;

/**
 * Model class representing a product.
 * 
 * This class stores product information such as name, price, and stock levels.
 * It serves as the base class for specialized product types.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Product {

    private int id;             
    private int productNo;     
    private String name;
    private int minStock;
    private int reservedStock;
    private double price;


    /**
     * Constructor initializing a Product with all details.
     * 
     * @param id the unique identifier
     * @param productNo the product number
     * @param name the product name
     * @param minStock the minimum stock level
     * @param reservedStock the reserved stock quantity
     * @param price the product price
     */
    public Product(int id, int productNo, String name, int minStock, int reservedStock, double price) {
        this.id = id;
        this.productNo = productNo;
        this.name = name;
        this.minStock = minStock;
        this.reservedStock = reservedStock;
        this.price = price;
    }

    /**
     * Gets the product ID.
     * 
     * @return the unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the product number.
     * 
     * @return the product number
     */
    public int getProductNo() {
        return productNo;
    }

    /**
     * Gets the product name.
     * 
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the minimum stock level.
     * 
     * @return the minimum stock
     */
    public int getMinStock() {
        return minStock;
    }

    /**
     * Gets the reserved stock quantity.
     * 
     * @return the reserved stock
     */
    public int getReservedStock() {
        return reservedStock;
    }
    
    /**
     * Gets the product price.
     * 
     * @return the price
     */
    public double getPrice() {
        return price;
    }


    /**
     * Sets the product ID.
     * 
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the product number.
     * 
     * @param productNo the product number to set
     */
    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    /**
     * Gets the product price for a specific date.
     * 
     * @param date the date to get the price for
     * @return the price (implementation pending)
     */
    public double getPrice(LocalDate date) {
        return 0; // implement later
    }

    /**
     * Calculates the total available stock.
     * 
     * @return the total available stock (implementation pending)
     */
    public int calculateTotalAvailableStock() {
        return 0; // implement later
    }
}
