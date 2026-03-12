package db;

import model.Order;
import java.sql.*;

public class OrderDB {

    public Order findOrder(int orderNo) {
        Order o = null;
        String sql = "SELECT * FROM SaleOrder WHERE orderNo = ?";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, orderNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                o = buildOrder(rs);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    public void insertOrder(Order o) {
        // ✔ DO NOT insert orderNo (IDENTITY)
        String sql = "INSERT INTO SaleOrder (customer_id, date, amount, discountGiven) VALUES (?, ?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            // ✔ Ask SQL Server to return the generated orderNo
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // ✔ Use customer ID, not phone number
            ps.setInt(1, o.getCustomer().getId());

            ps.setTimestamp(2, Timestamp.valueOf(o.getDate()));
            ps.setDouble(3, o.getAmount());
            ps.setDouble(4, o.getDiscountGiven());

            ps.executeUpdate();

            // ✔ Retrieve generated orderNo
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                o.setOrderNo(rs.getInt(1));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Order buildOrder(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setOrderNo(rs.getInt("orderNo"));
        o.setDate(rs.getTimestamp("date").toLocalDateTime());
        o.setDiscountGiven(rs.getDouble("discountGiven"));
        return o;
    }
}
