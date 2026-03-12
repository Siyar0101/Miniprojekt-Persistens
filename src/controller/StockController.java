package controller;

import db.StockDB;
import model.Stock;
import model.OrderLine;
import java.util.List;

/**
 * Controller class for managing stock-related operations.
 * 
 * This class handles stock validation, retrieval, and updates.
 * It coordinates between the stock database layer and other controllers.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class StockController {

    private StockDB sDB;

    /**
     * Constructor initializing the stock controller.
     */
    public StockController() {
        sDB = new StockDB();
    }

    /**
     * Checks if sufficient stock is available for an order line.
     * 
     * @param orderLine the OrderLine to check stock for
     * @param warehouseId the warehouse to check stock in
     * @return true if sufficient stock is available, false otherwise
     */
    public boolean hasEnoughStock(OrderLine orderLine, int warehouseId) {
        int productId = orderLine.getProduct().getId();
        int requestedQty = orderLine.getQuantity();
        int availableQty = sDB.getAvailableQuantity(productId, warehouseId);
        
        if (availableQty < 0) {
            System.out.println("Stock not found for product: " + productId);
            return false;
        }
        
        return availableQty >= requestedQty;
    }

    /**
     * Checks if sufficient stock is available for all order lines.
     * 
     * @param orderLines the list of OrderLines to check
     * @param warehouseId the warehouse to check stock in
     * @return true if all order lines have sufficient stock, false otherwise
     */
    public boolean hasEnoughStockForAllLines(List<OrderLine> orderLines, int warehouseId) {
        for (OrderLine ol : orderLines) {
            if (!hasEnoughStock(ol, warehouseId)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the available quantity of a product in a warehouse.
     * 
     * @param productId the ID of the product
     * @param warehouseId the ID of the warehouse
     * @return the available quantity, or -1 if stock not found
     */
    public int getAvailableQuantity(int productId, int warehouseId) {
        return sDB.getAvailableQuantity(productId, warehouseId);
    }

    /**
     * Decreases stock for a product when an order is confirmed.
     * This should be called in the order confirmation process.
     * Includes safety checks to prevent overselling and negative stock.
     * 
     * @param productId the ID of the product
     * @param warehouseId the ID of the warehouse
     * @param quantityToDeduct the quantity to subtract from stock
     * @return true if successful, false if insufficient stock or invalid parameters
     */
    public boolean decreaseStock(int productId, int warehouseId, int quantityToDeduct) {
        // Safety check: quantity must be positive
        if (quantityToDeduct <= 0) {
            System.out.println("Invalid quantity to deduct: " + quantityToDeduct + ". Quantity must be positive.");
            return false;
        }

        return sDB.decreaseStock(productId, warehouseId, quantityToDeduct);
    }

    /**
     * Processes stock reduction for all order lines in an order.
     * Should be called when an order is confirmed.
     * 
     * @param orderLines the list of OrderLines from the order
     * @param warehouseId the warehouse to deduct stock from
     * @return true if all lines processed successfully, false if any failed
     */
    public boolean processOrderLineStock(List<OrderLine> orderLines, int warehouseId) {
        for (OrderLine ol : orderLines) {
            int productId = ol.getProduct().getId();
            int quantity = ol.getQuantity();
            
            if (!decreaseStock(productId, warehouseId, quantity)) {
                System.out.println("Failed to process stock for product: " + productId);
                return false;
            }
        }
        return true;
    }

    /**
     * Gets all stock items for a specific product.
     * 
     * @param productId the ID of the product
     * @return a List of Stock objects for this product across all warehouses
     */
    public List<Stock> getProductStock(int productId) {
        return sDB.findStockByProduct(productId);
    }

    /**
     * Gets stock for a specific product in a specific warehouse.
     * 
     * @param productId the ID of the product
     * @param warehouseId the ID of the warehouse
     * @return the Stock object if found, null otherwise
     */
    public Stock getStock(int productId, int warehouseId) {
        return sDB.findStock(productId, warehouseId);
    }

    /**
     * Validates that a requested quantity is valid for a product.
     * Prevents adding zero or negative quantities to orders.
     * 
     * @param quantity the quantity to validate
     * @return true if quantity is valid (positive), false otherwise
     */
    public boolean isValidQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity: " + quantity + ". Quantity must be greater than 0.");
            return false;
        }
        return true;
    }
}
