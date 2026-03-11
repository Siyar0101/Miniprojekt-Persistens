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
        String sql = "INSERT INTO SaleOrder (orderNo, customerPhoneNo, date, amount, discountGiven) VALUES (?, ?, ?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, o.getOrderNo());
            ps.setString(2, o.getCustomer().getPhoneNo());
            ps.setTimestamp(3, Timestamp.valueOf(o.getDate()));
            ps.setDouble(4, o.getAmount());
            ps.setDouble(5, o.getDiscountGiven());

            ps.executeUpdate();
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
