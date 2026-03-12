package db;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database access class for Product entity.
 * 
 * This class handles all database operations related to Product objects,
 * including finding products by product number and retrieving all products.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class ProductDB {

    /**
     * Finds a product in the database by product number.
     * 
     * @param productNo the product number to search for
     * @return the Product object if found, null otherwise
     */
    public Product findProduct(int productNo) {
        Product p = null;
        String sql = "SELECT * FROM Product WHERE productNo = ?";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, productNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = buildProduct(rs);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * Retrieves all products from the database.
     * 
     * @return a List containing all Product objects
     */
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(buildProduct(rs));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Builds a Product object from a ResultSet row.
     * 
     * @param rs the ResultSet containing product data
     * @return a new Product object with data from the ResultSet
     * @throws SQLException if a database access error occurs
     */
    private Product buildProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("id"),
            rs.getInt("productNo"),
            rs.getString("name"),
            rs.getInt("minStock"),
            rs.getInt("reservedStock"),
            rs.getDouble("price")     
        );
    }

}
