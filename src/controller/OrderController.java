package controller;

import db.OrderDB;
import db.OrderLineDB;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;

/**
 * Controller class for managing order-related operations.
 * 
 * This class handles the creation, modification, and confirmation of orders.
 * It coordinates between the customer controller, product controller, stock controller, and database layer.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class OrderController {

    private CustomerController cCtrl;
    private ProductController pCtrl;
    private StockController sCtrl;
    private OrderDB oDB;
    private Order order;
    private static final int DEFAULT_WAREHOUSE_ID = 1; // Default warehouse - adjust as needed

    /**
     * Constructor initializing the order controller with required dependencies.
     */
    public OrderController() {
        cCtrl = new CustomerController();
        pCtrl = new ProductController();
        sCtrl = new StockController();
        oDB = new OrderDB();
    }

    /**
     * Creates a new order.
     * 
     * @return void
     */
    public void placeOrder() {
        order = new Order();
    }

    /**
     * Cancels the current order.
     * 
     * @return void
     */
    public void cancelOrder() {
        // Cancel current order by clearing the reference
        order = null;
    }

    /**
     * Adds a customer to the current order by phone number.
     * 
     * @param phoneNo the phone number of the customer to add
     * @return the Customer object if found and added, null otherwise
     */
    public Customer addCustomer(String phoneNo) {
        Customer c = cCtrl.findCustomer(phoneNo);
        if (c != null) {
            order.addCustomer(c);   // ✔ stores the actual Customer object
        }
        return c;
    }

    /**
     * Adds a customer to the current order by ID.
     * 
     * @param id the unique identifier of the customer
     * @return the Customer object if found and added, null otherwise
     */
    public Customer addCustomerById(int id) {
        Customer c = cCtrl.findCustomerById(id);
        if (c != null) {
            order.addCustomer(c);   // ✔ same fix
        }
        return c;
    }

    /**
     * Adds a product to the current order.
     * Also checks if sufficient stock is available.
     * 
     * @param productNo the product number to add
     * @param qty the quantity to add
     * @return the OrderLine object if product is found and stock is available, null otherwise
     */
    public OrderLine addProduct(int productNo, int qty) {
        // Check if order exists
        if (order == null) {
            System.out.println("No active order. Please place an order first.");
            return null;
        }

        // Validate quantity first
        if (!sCtrl.isValidQuantity(qty)) {
            return null;
        }

        Product p = pCtrl.findProduct(productNo);
        if (p == null) {
            System.out.println("Product not found: " + productNo);
            return null;
        }

        // Check if sufficient stock is available
        int availableQty = sCtrl.getAvailableQuantity(p.getId(), DEFAULT_WAREHOUSE_ID);
        if (availableQty < 0) {
            System.out.println("Stock information not available for product: " + productNo);
            return null;
        }
        
        if (availableQty < qty) {
            System.out.println("Insufficient stock for product: " + productNo + 
                             ". Available: " + availableQty + ", Requested: " + qty);
            return null;
        }

        OrderLine ol = new OrderLine(p, qty);
        order.addOrderLine(ol);
        return ol;
    }

    /**
     * Confirms and saves the current order to the database.
     * Also updates stock quantities for each order line.
     * 
     * @return the confirmed Order object if successful, null if no active order
     */
    public Order confirmOrder() {
        if (order == null) {
            System.out.println("No active order to confirm");
            return null;
        }

        // Verify all products have sufficient stock before confirming
        if (!sCtrl.hasEnoughStockForAllLines(order.getOrderLines(), DEFAULT_WAREHOUSE_ID)) {
            System.out.println("Cannot confirm order: insufficient stock for one or more items");
            return null;
        }

        // Calculate totals and discounts before saving
        order.calculateTotal();

        // Insert order first (this generates order.id)
        oDB.insertOrder(order);

        // Insert each order line using order.getId()
        OrderLineDB olDB = new OrderLineDB();
        for (OrderLine ol : order.getOrderLines()) {
            olDB.insertOrderLine(ol, order.getId());

            // Update stock after order line is inserted
            int productId = ol.getProduct().getId();
            int quantityOrdered = ol.getQuantity();
            if (!sCtrl.decreaseStock(productId, DEFAULT_WAREHOUSE_ID, quantityOrdered)) {
                System.out.println("Warning: Failed to update stock for product: " + productId);
            }
        }

        return order;
    }
}