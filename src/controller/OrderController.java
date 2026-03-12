package controller;

import db.OrderDB;
import db.OrderLineDB;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;

public class OrderController {

    private CustomerController cCtrl;
    private ProductController pCtrl;
    private OrderDB oDB;
    private Order order;

    public OrderController() {
        cCtrl = new CustomerController();
        pCtrl = new ProductController();
        oDB = new OrderDB();
    }

    public void placeOrder() {
        order = new Order();
    }

    public Customer addCustomer(String phoneNo) {
        Customer c = cCtrl.findCustomer(phoneNo);
        if (c != null) {
            order.addCustomer(c);   // ✔ stores the actual Customer object
        }
        return c;
    }

    public Customer addCustomerById(int id) {
        Customer c = cCtrl.findCustomerById(id);
        if (c != null) {
            order.addCustomer(c);   // ✔ same fix
        }
        return c;
    }

    public OrderLine addProduct(int productNo, int qty) {
        Product p = pCtrl.findProduct(productNo);
        if (p == null) return null;

        OrderLine ol = new OrderLine(p, qty);
        order.addOrderLine(ol);
        return ol;
    }

    public Order confirmOrder() {
        // ✔ Insert order first (this generates order.id)
        oDB.insertOrder(order);

        // ✔ Insert each order line using order.getId()
        OrderLineDB olDB = new OrderLineDB();
        for (OrderLine ol : order.getOrderLines()) {
            olDB.insertOrderLine(ol, order.getId());
        }

        return order;
    }
}
