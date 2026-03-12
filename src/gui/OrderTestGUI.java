package gui;

import controller.OrderController;
import model.OrderLine;

import javax.swing.*;
import java.awt.*;

public class OrderTestGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private OrderController oCtrl = new OrderController();

    private JTextField txtCustomerId = new JTextField(10);
    private JTextField txtProductNo = new JTextField(5);
    private JTextField txtQty = new JTextField(5);
    private JTextArea output = new JTextArea(10, 30);

    public OrderTestGUI() {
        setTitle("Order Test GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Customer ID:"));
        panel.add(txtCustomerId);

        panel.add(new JLabel("Product No:"));
        panel.add(txtProductNo);

        panel.add(new JLabel("Quantity:"));
        panel.add(txtQty);

        JButton btnNewOrder = new JButton("New Order");
        JButton btnAddCustomer = new JButton("Add Customer");
        JButton btnAddProduct = new JButton("Add Product");
        JButton btnConfirm = new JButton("Confirm Order");

        // Start new order
        btnNewOrder.addActionListener(e -> {
            oCtrl.placeOrder();
            output.append("New order started\n");
        });

        // Add customer
        btnAddCustomer.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtCustomerId.getText());
                var c = oCtrl.addCustomerById(id);

                if (c != null) {
                    output.append("Customer added: " + c.getName() + "\n");
                } else {
                    output.append("Customer NOT FOUND\n");
                }

            } catch (Exception ex) {
                output.append("Invalid customer ID\n");
            }
        });

        // Add product
        btnAddProduct.addActionListener(e -> {
            try {
                int pNo = Integer.parseInt(txtProductNo.getText());
                int qty = Integer.parseInt(txtQty.getText());

                OrderLine ol = oCtrl.addProduct(pNo, qty);

                if (ol != null) {
                    double price = ol.getProduct().getPrice();
                    double subtotal = ol.calculateSubtotal();

                    output.append("Product added: "
                            + ol.getProduct().getName()
                            + " (" + ol.getProduct().getProductNo() + ") x"
                            + ol.getQuantity()
                            + " | Price: " + price
                            + " | Subtotal: " + subtotal
                            + "\n");
                } else {
                    output.append("Product NOT FOUND\n");
                }

            } catch (Exception ex) {
                output.append("Invalid product or quantity\n");
            }
        });

        // Confirm order
        btnConfirm.addActionListener(e -> {
            var order = oCtrl.confirmOrder();
            output.append("Order confirmed. Total: " + order.getAmount() + "\n");
        });

        JPanel buttons = new JPanel();
        buttons.add(btnNewOrder);
        buttons.add(btnAddCustomer);
        buttons.add(btnAddProduct);
        buttons.add(btnConfirm);

        add(panel, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        add(new JScrollPane(output), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OrderTestGUI();
    }
}
