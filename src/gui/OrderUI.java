package gui;

import controller.OrderController;

/**
 * User interface class for order management.
 * 
 * This class provides a simple interface for placing orders, adding customers and products,
 * and confirming orders. It delegates operations to the OrderController.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class OrderUI {
    private OrderController oCtrl;

    /**
     * Constructor initializing the OrderUI with an OrderController.
     */
    public OrderUI() {
        oCtrl = new OrderController();
    }

    /**
     * Places a new order.
     * 
     * @return void
     */
    public void placeOrder() {
        oCtrl.placeOrder();
    }

    /**
     * Adds a customer to the current order.
     * 
     * @param phoneNo the phone number of the customer to add
     * @return void
     */
    public void addCustomer(String phoneNo) {
        oCtrl.addCustomer(phoneNo);
    }

    /**
     * Adds a product to the current order.
     * 
     * @param productNo the product number to add
     * @param qty the quantity to add
     * @return void
     */
    public void addProduct(int productNo, int qty) {
        oCtrl.addProduct(productNo, qty);
    }

    /**
     * Confirms the current order.
     * 
     * @return void
     */
    public void confirmOrder() {
        oCtrl.confirmOrder();
    }
}

