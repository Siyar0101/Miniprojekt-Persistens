package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;

public class OrderDB {

    private static OrderDB instance;

    private OrderDB() {
        // no DBConnection stored here because your DBConnection is not a singleton
    }

    public static synchronized OrderDB getInstance() {
        if (instance == null) {
            instance = new OrderDB();
        }
        return instance;
    }

    // ---------------------------------------------------------
    // FIND ONE ORDER
    // ---------------------------------------------------------
    public Order findOrder(int orderNo) {
        Order order = null;

        try {
            String sql = "SELECT * FROM Orders WHERE orderNo = ?";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderNo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = buildOrder(rs);
            }

            rs.close();
            stmt.close();
            conn.close();

            if (order != null) {
                getOrderLines(order);
                order.calculateTotal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    // ---------------------------------------------------------
    // GET ALL ORDERS
    // ---------------------------------------------------------
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Orders";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order o = buildOrder(rs);
                getOrderLines(o);
                o.calculateTotal();
                orders.add(o);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    // ---------------------------------------------------------
    // INSERT ORDER (sets generated orderNo)
    // ---------------------------------------------------------
    public void insertOrder(Order order) {
        try {
            String sql = "INSERT INTO Orders (phoneNo, orderDate, amount, discountGiven) VALUES (?, ?, ?, ?)";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, order.getC().getPhoneNo());
            stmt.setTimestamp(2, Timestamp.valueOf(order.getDate()));
            stmt.setDouble(3, order.getAmount());
            stmt.setDouble(4, order.getDiscountGiven());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                order.setOrderNo(keys.getInt(1));
            }

            keys.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // PRIVATE HELPERS
    // ---------------------------------------------------------
    private Order buildOrder(ResultSet rs) throws Exception {
        Order o = new Order();

        o.setOrderNo(rs.getInt("orderNo"));
        o.setDate(rs.getTimestamp("orderDate").toLocalDateTime());
        o.setDiscountGiven(rs.getDouble("discountGiven"));

        String phoneNo = rs.getString("phoneNo");
        Customer c = CustomerDB.getInstance().findCustomer(phoneNo);
        o.addCustomer(c);

        return o;
    }

    private void getOrderLines(Order order) throws Exception {
        String sql = "SELECT * FROM OrderLines WHERE orderNo = ?";
        Connection conn = new DBConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, order.getOrderNo());

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int productNo = rs.getInt("productNo");
            int qty = rs.getInt("qty");

            Product p = ProductDB.getInstance().findProduct(productNo);
            OrderLine ol = new OrderLine(p, qty);

            order.addOrderLine(ol);
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}
