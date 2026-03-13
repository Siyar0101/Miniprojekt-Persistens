package gui;

import controller.OrderController;
import model.OrderLine;

import javax.swing.*;
import java.awt.*;

/**
 * Test GUI for order management.
 * 
 * This class provides a graphical user interface for testing order functionality,
 * including adding customers, products, and confirming orders.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class OrderTestGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private OrderController oCtrl = new OrderController();

    private JTextField txtCustomerId = new JTextField(10);
    private JTextField txtProductNo = new JTextField(5);
    private JTextField txtQty = new JTextField(5);
    private JTextArea output = new JTextArea(10, 30);

    // Helper to format currency values with two decimals and " kr." suffix
    private String fmtCurrency(double v) {
        return String.format("%.2f kr.", v);
    }

    /**
     * Constructor initializing the OrderTestGUI window and components.
     */
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
        JButton btnCancel = new JButton("Cancel Order");

        // Start new order
        btnNewOrder.addActionListener(e -> {
            e.getActionCommand(); // use parameter to avoid "unused lambda parameter" warning
            oCtrl.placeOrder();
            output.append("New order started\n");
        });

        // Add customer
        btnAddCustomer.addActionListener(e -> {
            e.getActionCommand(); // use parameter to avoid "unused lambda parameter" warning
            try {
                int id = Integer.parseInt(txtCustomerId.getText());
                var c = oCtrl.addCustomerById(id);

                if (c != null) {
                    String type = c.getCustomerType() == null ? "unknown" : c.getCustomerType();
                    output.append("Customer added: " + c.getName() + " [" + type + "]\n");
                } else {
                    output.append("Customer NOT FOUND\n");
                }

            } catch (Exception ex) {
                output.append("Invalid customer ID\n");
            }
        });

        // Add product
        btnAddProduct.addActionListener(e -> {
            e.getActionCommand(); // use parameter to avoid "unused lambda parameter" warning
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
                            + " | Price: " + fmtCurrency(price)
                            + " | Subtotal: " + fmtCurrency(subtotal)
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
            e.getActionCommand(); // use parameter to avoid "unused lambda parameter" warning
            var order = oCtrl.confirmOrder();
            if (order == null) {
                output.append("No active order to confirm\n");
                return;
            }

            // Compute subtotal from order lines for clarity
            double subtotal = 0.0;
            if (order.getOrderLines() != null) {
                for (var ol : order.getOrderLines()) {
                    subtotal += ol.calculateSubtotal();
                }
            }

            double amount = order.getAmount();
            double discount = order.getDiscountGiven();

            String customerType = "(no customer)";
            if (order.getCustomer() != null && order.getCustomer().getCustomerType() != null) {
                customerType = order.getCustomer().getCustomerType();
            }

            String msg = "Order confirmed. Total: " + fmtCurrency(amount);
            // Do not display customer type anymore; only show subtotal
            msg += " | Subtotal: " + fmtCurrency(subtotal);

            if (discount > 0.0) {
                double percent = (discount / subtotal) * 100.0;
                msg += String.format(" | Discount: %s (%.0f%%)", fmtCurrency(discount), percent);

            } else {
                // Explain why no discount was applied
                if (!customerType.equalsIgnoreCase("club")) {
                    msg += " | No discount: customer is not 'club'";
                } else if (subtotal <= 1500.0) {
                    msg += " | No discount: subtotal not high enough";
                }
            }

            output.append(msg + "\n");
        });

        // Cancel order
        btnCancel.addActionListener(e -> {
            e.getActionCommand(); // use parameter to avoid "unused lambda parameter" warning
            if (JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to cancel the current order?",
                    "Confirm cancel",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                oCtrl.cancelOrder();
                output.append("Order cancelled\n");
            } else {
                output.append("Order not cancelled\n");
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(btnNewOrder);
        buttons.add(btnAddCustomer);
        buttons.add(btnAddProduct);
        buttons.add(btnConfirm);
        buttons.add(btnCancel);

        add(panel, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        add(new JScrollPane(output), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Main method to start the OrderTestGUI application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new OrderTestGUI();
    }
}