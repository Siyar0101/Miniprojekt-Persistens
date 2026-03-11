package db;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {

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

    private Product buildProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("productNo"),
                rs.getString("name"),
                rs.getInt("minStock"),
                rs.getInt("reservedStock")
        );
    }
}
