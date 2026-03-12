package db;

import model.OrderLine;
import java.sql.*;

/**
 * Database access class for OrderLine entity.
 * 
 * This class handles all database operations related to OrderLine objects,
 * including inserting order lines into the database.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class OrderLineDB {

    /**
     * Inserts a new order line into the database.
     * 
     * @param ol the OrderLine object to insert
     * @param orderId the ID of the order this line belongs to
     */
    public void insertOrderLine(OrderLine ol, int orderId) {
        String sql = "INSERT INTO OrderLine (saleorder_id, product_id, quantity) VALUES (?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, orderId);                    
            ps.setInt(2, ol.getProduct().getId());     
            ps.setInt(3, ol.getQuantity());

            ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
