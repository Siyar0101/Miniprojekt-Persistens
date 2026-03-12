package db;

import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {

	private static CustomerDB instance;

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

	private Customer buildCustomer(ResultSet rs) throws SQLException {
		return new Customer(rs.getString("name"), rs.getString("address"), rs.getInt("zipcode"), rs.getString("city"),
				rs.getString("phoneNo"), rs.getString("email"), rs.getString("customerType"));
	}
}