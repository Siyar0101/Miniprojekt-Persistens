package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDB {

    private static CustomerDB instance;

    private CustomerDB() {}

    public static synchronized CustomerDB getInstance() {
        if (instance == null) {
            instance = new CustomerDB();
        }
        return instance;
    }

    // ---------------------------------------------------------
    // FIND ONE CUSTOMER
    // ---------------------------------------------------------
    public Customer findCustomer(String phoneNo) {
        Customer customer = null;

        try {
            String sql = "SELECT * FROM Customers WHERE phoneNo = ?";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phoneNo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = buildCustomer(rs);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }

    // ---------------------------------------------------------
    // GET ALL CUSTOMERS
    // ---------------------------------------------------------
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Customers";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customers.add(buildCustomer(rs));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    // ---------------------------------------------------------
    // INSERT CUSTOMER
    // ---------------------------------------------------------
    public void insertCustomer(Customer c) {
        try {
            String sql = "INSERT INTO Customers (name, address, zipcode, city, phoneNo, email, customerType) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, c.getName());
            stmt.setString(2, c.getAddress());
            stmt.setInt(3, c.getZipcode());
            stmt.setString(4, c.getCity());
            stmt.setString(5, c.getPhoneNo());
            stmt.setString(6, c.getEmail());
            stmt.setString(7, c.getCustomerType());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // UPDATE CUSTOMER
    // ---------------------------------------------------------
    public void updateCustomer(Customer c) {
        try {
            String sql = "UPDATE Customers SET name=?, address=?, zipcode=?, city=?, email=?, customerType=? "
                       + "WHERE phoneNo=?";

            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, c.getName());
            stmt.setString(2, c.getAddress());
            stmt.setInt(3, c.getZipcode());
            stmt.setString(4, c.getCity());
            stmt.setString(5, c.getEmail());
            stmt.setString(6, c.getCustomerType());
            stmt.setString(7, c.getPhoneNo());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // DELETE CUSTOMER
    // ---------------------------------------------------------
    public void deleteCustomer(String phoneNo) {
        try {
            String sql = "DELETE FROM Customers WHERE phoneNo = ?";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, phoneNo);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // BUILD CUSTOMER FROM RESULTSET
    // ---------------------------------------------------------
    private Customer buildCustomer(ResultSet rs) throws Exception {
        String name = rs.getString("name");
        String address = rs.getString("address");
        int zipcode = rs.getInt("zipcode");
        String city = rs.getString("city");
        String phoneNo = rs.getString("phoneNo");
        String email = rs.getString("email");
        String customerType = rs.getString("customerType");

        return new Customer(name, address, zipcode, city, phoneNo, email, customerType);
    }
}
