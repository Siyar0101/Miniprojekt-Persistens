package db;

import model.OrderLine;
import java.sql.*;

public class OrderLineDB {

    public void insertOrderLine(OrderLine ol, int orderNo) {
        String sql = "INSERT INTO SaleOrderLine (saleorder_id, product_id, quantity) VALUES (?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // ✔ saleorder_id → the order number from SaleOrder
            ps.setInt(1, orderNo);

            // ✔ product_id → the product number from Product
            ps.setInt(2, ol.getProduct().getProductNo());

            // ✔ quantity → the quantity from OrderLine
            ps.setInt(3, ol.getQuantity());

            ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
