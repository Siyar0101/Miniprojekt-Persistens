package gui;


import controller.OrderController;
import javax.swing.*;
import java.awt.*;

public class OrderTestGUI extends JFrame {

    private static final long serialVersionUID = 1L;

	private OrderController oCtrl = new OrderController();

    private JTextField txtPhone = new JTextField(10);
    private JTextField txtProductNo = new JTextField(5);
    private JTextField txtQty = new JTextField(5);
    private JTextArea output = new JTextArea(10, 30);

    public OrderTestGUI() {
        setTitle("Order Test GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Customer Phone:"));
        panel.add(txtPhone);

        panel.add(new JLabel("Product No:"));
        panel.add(txtProductNo);

        panel.add(new JLabel("Quantity:"));
        panel.add(txtQty);

        JButton btnNewOrder = new JButton("New Order");
        JButton btnAddCustomer = new JButton("Add Customer");
        JButton btnAddProduct = new JButton("Add Product");
        JButton btnConfirm = new JButton("Confirm Order");

        btnNewOrder.addActionListener(e -> {
            oCtrl.placeOrder();
            output.append("New order started\n");
        });

        btnAddCustomer.addActionListener(e -> {
            var c = oCtrl.addCustomer(txtPhone.getText());
            output.append("Customer added: " + (c != null ? c.getName() : "NOT FOUND") + "\n");
        });

        btnAddProduct.addActionListener(e -> {
            try {
                int pNo = Integer.parseInt(txtProductNo.getText());
                int qty = Integer.parseInt(txtQty.getText());
                var ol = oCtrl.addProduct(pNo, qty);
                output.append("Added product: " + pNo + " x" + qty + "\n");
            } catch (Exception ex) {
                output.append("Invalid product or quantity\n");
            }
        });

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
