package db;

import model.Stock;
import model.Product;
import model.Warehouse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database access class for Stock entity.
 * 
 * This class handles all database operations related to Stock objects,
 * including finding stock items by product/warehouse, checking quantities,
 * and updating stock after orders are confirmed.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class StockDB {

    /**
     * Finds stock for a specific product in a specific warehouse.
     * 
     * @param productId the ID of the product
     * @param warehouseId the ID of the warehouse
     * @return the Stock object if found, null otherwise
     */
    public Stock findStock(int productId, int warehouseId) {
        Stock s = null;
        String sql = "SELECT * FROM Stock WHERE product_id = ? AND warehouse_id = ?";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, productId);
            ps.setInt(2, warehouseId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s = buildStock(rs);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Retrieves all stock items for a specific product across all warehouses.
     * 
     * @param productId the ID of the product
     * @return a List of Stock objects for this product
     */
    public List<Stock> findStockByProduct(int productId) {
        List<Stock> list = new ArrayList<>();
        String sql = "SELECT * FROM Stock WHERE product_id = ?";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(buildStock(rs));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Gets the available quantity of a product in a warehouse.
     * Checks if quantity exists and is sufficient.
     * 
     * @param productId the ID of the product
     * @param warehouseId the ID of the warehouse
     * @return the available quantity, or -1 if stock not found
     */
    public int getAvailableQuantity(int productId, int warehouseId) {
        String sql = "SELECT availableQty FROM Stock WHERE product_id = ? AND warehouse_id = ?";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, productId);
            ps.setInt(2, warehouseId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int qty = rs.getInt("availableQty");
                con.close();
                return qty;
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Stock not found
    }

    /**
     * Updates the available quantity for stock in the database.
     * This is called when an order is confirmed.
     * Prevents setting negative stock quantities.
     * 
     * @param productId the ID of the product
     * @param warehouseId the ID of the warehouse
     * @param newQuantity the new quantity to set
     * @return true if successful, false if quantity is negative or stock not found
     */
    public boolean updateStock(int productId, int warehouseId, int newQuantity) {
        // Safety check: prevent negative stock
        if (newQuantity < 0) {
            System.out.println("Invalid quantity: " + newQuantity + ". Stock cannot be negative.");
            return false;
        }

        // Verify stock exists
        Stock stock = findStock(productId, warehouseId);
        if (stock == null) {
            System.out.println("Stock not found for product: " + productId + " in warehouse: " + warehouseId);
            return false;
        }

        String sql = "UPDATE Stock SET availableQty = ? WHERE product_id = ? AND warehouse_id = ?";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, newQuantity);
            ps.setInt(2, productId);
            ps.setInt(3, warehouseId);

            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Decreases stock for a product by a specified amount.
     * Used when an order line is added to an order.
     * 
     * @param productId the ID of the product
     * @param warehouseId the ID of the warehouse
     * @param quantityToDeduct the quantity to subtract from stock
     * @return true if successful, false otherwise
     */
    public boolean decreaseStock(int productId, int warehouseId, int quantityToDeduct) {
        int currentQty = getAvailableQuantity(productId, warehouseId);
        
        if (currentQty == -1) {
            System.out.println("Stock not found for product: " + productId);
            return false;
        }
        
        if (currentQty < quantityToDeduct) {
            System.out.println("Insufficient stock. Available: " + currentQty + ", Requested: " + quantityToDeduct);
            return false;
        }

        int newQty = currentQty - quantityToDeduct;
        updateStock(productId, warehouseId, newQty);
        return true;
    }

    /**
     * Builds a Stock object from a ResultSet row.
     * 
     * @param rs the ResultSet containing stock data
     * @return a new Stock object with data from the ResultSet
     * @throws SQLException if a database access error occurs
     */
    private Stock buildStock(ResultSet rs) throws SQLException {
        ProductDB pDB = new ProductDB();
        Product p = pDB.findProduct(rs.getInt("product_id"));

        // Create a basic Warehouse object - you may need to fetch full details from DB
        Warehouse w = new Warehouse(
            String.valueOf(rs.getInt("warehouse_id")),
            "Warehouse",
            ""
        );

        return new Stock(
            p,
            w,
            rs.getInt("availableQty")
        );
    }
}
