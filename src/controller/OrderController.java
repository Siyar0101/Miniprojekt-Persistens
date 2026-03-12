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
 * It coordinates between the customer controller, product controller, and database layer.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class OrderController {

    private CustomerController cCtrl;
    private ProductController pCtrl;
    private OrderDB oDB;
    private Order order;

    /**
     * Constructor initializing the order controller with required dependencies.
     */
    public OrderController() {
        cCtrl = new CustomerController();
        pCtrl = new ProductController();
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
     * 
     * @param productNo the product number to add
     * @param qty the quantity to add
     * @return the OrderLine object if product is found, null otherwise
     */
    public OrderLine addProduct(int productNo, int qty) {
        Product p = pCtrl.findProduct(productNo);
        if (p == null) return null;

        OrderLine ol = new OrderLine(p, qty);
        order.addOrderLine(ol);
        return ol;
    }

    /**
     * Confirms and saves the current order to the database.
     * 
     * @return the confirmed Order object if successful, null if no active order
     */
    public Order confirmOrder() {
        if (order == null) {
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
        }

        return order;
    }
}