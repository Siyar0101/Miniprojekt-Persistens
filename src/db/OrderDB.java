package db;

import model.Order;
import java.sql.*;

public class OrderDB {

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
				o.setId(rs.getInt(1)); // ✔ store identity id
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Order buildOrder(ResultSet rs) throws SQLException {
		Order o = new Order();
		o.setId(rs.getInt("id"));
		o.setDate(rs.getTimestamp("date").toLocalDateTime());
		o.setDiscountGiven(rs.getDouble("discountGiven"));
		o.setAmount(rs.getDouble("amount"));
		return o;
	}
}
