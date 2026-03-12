package db;

import model.Customer;
import model.Order;
import java.sql.*;

/**
 * Database access class for Order entity.
 * 
 * This class handles all database operations related to Order objects,
 * including finding orders by ID and inserting new orders.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class OrderDB {

	/**
	 * Finds an order in the database by ID.
	 * 
	 * @param id the unique identifier of the order
	 * @return the Order object if found, null otherwise
	 */
	public Order findOrder(int id) {
		Order o = null;
		String sql = "SELECT * FROM SaleOrder WHERE id = ?";

		try {
			DBConnection db = new DBConnection();
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);
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

	/**
	 * Inserts a new order into the database.
	 * 
	 * @param o the Order object to insert
	 */
	public void insertOrder(Order o) {
		String sql = "INSERT INTO SaleOrder (customer_id, date, amount, discountGiven) VALUES (?, ?, ?, ?)";

		try {
			DBConnection db = new DBConnection();
			Connection con = db.getConnection();

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, o.getCustomer().getId());
			ps.setTimestamp(2, Timestamp.valueOf(o.getDate()));
			ps.setDouble(3, o.getAmount());
			ps.setDouble(4, o.getDiscountGiven());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				o.setId(rs.getInt(1)); 
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Builds an Order object from a ResultSet row.
	 * 
	 * @param rs the ResultSet containing order data
	 * @return a new Order object with data from the ResultSet
	 * @throws SQLException if a database access error occurs
	 */
	private Order buildOrder(ResultSet rs) throws SQLException {
	    Order o = new Order();
	    o.setId(rs.getInt("id"));
	    o.setDate(rs.getTimestamp("date").toLocalDateTime());
	    o.setDiscountGiven(rs.getDouble("discountGiven"));
	    o.setAmount(rs.getDouble("amount"));
	    
	    // Tilføj Customer fra database
	    int customerId = rs.getInt("customer_id");
	    Customer c = new CustomerDB().findCustomerById(customerId);
	    o.setCustomer(c);
	    
	    return o;
	}
}