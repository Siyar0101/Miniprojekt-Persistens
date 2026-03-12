package db;

import model.OrderLine;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderLineDB {


	public void insertOrderLine(OrderLine ol, int orderNo) {
		String sql = "INSERT INTO OrderLine (orderNo, productNo, quantity) VALUES (?, ?, ?)";

		try {
			DBConnection db = new DBConnection();
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, orderNo);
			ps.setInt(2, ol.getProduct().getProductNo());
			ps.setInt(3, ol.getQuantity());

			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<OrderLine> getOrderLines(int orderNo) {
		List<OrderLine> list = new ArrayList<>();
		String sql = "SELECT * FROM OrderLine WHERE orderNo = ?";

		try {
			DBConnection db = new DBConnection();
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, orderNo);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(buildOrderLine(rs));
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private OrderLine buildOrderLine(ResultSet rs) throws SQLException {
		Product p = new ProductDB().findProduct(rs.getInt("productNo"));
		return new OrderLine(p, rs.getInt("quantity"));
	}
}
