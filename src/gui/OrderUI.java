package gui;

import controller.OrderController;

public class OrderUI {
    private OrderController oCtrl;

    public OrderUI() {
        oCtrl = new OrderController();
    }

    public void placeOrder() {
        oCtrl.placeOrder();
    }

    public void addCustomer(String phoneNo) {
        oCtrl.addCustomer(phoneNo);
    }

    public void addProduct(int productNo, int qty) {
        oCtrl.addProduct(productNo, qty);
    }

    public void confirmOrder() {
        oCtrl.confirmOrder();
    }
}

