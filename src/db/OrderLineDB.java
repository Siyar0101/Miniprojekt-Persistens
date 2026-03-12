package db;

import model.OrderLine;
import java.sql.*;

public class OrderLineDB {

    public void insertOrderLine(OrderLine ol, int orderId) {
        String sql = "INSERT INTO OrderLine (saleorder_id, product_id, quantity) VALUES (?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, orderId);                     // ✔ uses SaleOrder.id
            ps.setInt(2, ol.getProduct().getId());     // ✔ uses Product.id (NOT productNo)
            ps.setInt(3, ol.getQuantity());

            ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
