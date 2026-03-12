package db;

import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database access class for Customer entity.
 * 
 * This class handles all database operations related to Customer objects,
 * including finding customers by phone number or ID, and retrieving all customers.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class CustomerDB {

    /**
     * Finds a customer in the database by phone number.
     * 
     * @param phoneNo the phone number of the customer to find
     * @return the Customer object if found, null otherwise
     */
    public Customer findCustomer(String phoneNo) {
        Customer c = null;
        String sql = "SELECT * FROM Customer WHERE phoneNo = ?";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, phoneNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = buildCustomer(rs);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Finds a customer in the database by ID.
     * 
     * @param id the unique identifier of the customer
     * @return the Customer object if found, null otherwise
     */
    public Customer findCustomerById(int id) {
        Customer c = null;
        String sql = "SELECT * FROM Customer WHERE id = ?";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = buildCustomer(rs);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Retrieves all customers from the database.
     * 
     * @return a List containing all Customer objects
     */
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(buildCustomer(rs));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Builds a Customer object from a ResultSet row.
     * 
     * @param rs the ResultSet containing customer data
     * @return a new Customer object with data from the ResultSet
     * @throws SQLException if a database access error occurs
     */
    private Customer buildCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("id"),           
                rs.getString("name"),
                rs.getString("address"),
                rs.getInt("zipcode"),
                rs.getString("city"),
                rs.getString("phoneNo"),
                rs.getString("email"),
                rs.getString("customerType")
        );
    }

}
