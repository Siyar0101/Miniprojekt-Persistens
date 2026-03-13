package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing a warehouse.
 * 
 * This class manages warehouse information and maintains a list of stock items
 * that are stored in the warehouse.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Payam Hamidi, Siyar Ustun
 * @version 1.0
 */
public class Warehouse {
    private String number;
    private String name;
    private String description;
    private List<Stock> stockItems;

    /**
     * Constructor initializing a Warehouse with details.
     * 
     * @param number the warehouse number
     * @param name the warehouse name
     * @param description the warehouse description
     */
    public Warehouse(String number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.stockItems = new ArrayList<>();
    }

    /**
     * Gets the warehouse number.
     * 
     * @return the warehouse number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Gets the warehouse name.
     * 
     * @return the warehouse name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the warehouse description.
     * 
     * @return the warehouse description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets all stock items in this warehouse.
     * 
     * @return a List of Stock objects
     */
    public List<Stock> getStockItems() {
        return stockItems;
    }

    /**
     * Adds a stock item to the warehouse.
     * 
     * @param stock the Stock item to add
     */
    public void addStockItem(Stock stock) {
        if (stock != null) {
            this.stockItems.add(stock);
        }
    }
}