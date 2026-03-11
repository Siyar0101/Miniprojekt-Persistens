package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Clothing;
import model.Equipment;
import model.GunReplica;
import model.Product;

public class ProductDB {

    private static ProductDB instance;

    private ProductDB() {}

    public static synchronized ProductDB getInstance() {
        if (instance == null) {
            instance = new ProductDB();
        }
        return instance;
    }

    // ---------------------------------------------------------
    // FIND ONE PRODUCT
    // ---------------------------------------------------------
    public Product findProduct(int productNo) {
        Product product = null;

        try {
            String sql = "SELECT * FROM Products WHERE productNo = ?";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productNo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = buildProduct(rs);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    // ---------------------------------------------------------
    // GET ALL PRODUCTS
    // ---------------------------------------------------------
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Products";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(buildProduct(rs));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    // ---------------------------------------------------------
    // INSERT PRODUCT
    // ---------------------------------------------------------
    public void insertProduct(Product p) {
        try {
            String sql = "INSERT INTO Products (name, minStock, reservedStock, type, size, colour, material, style, calibre) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, p.getName());
            stmt.setInt(2, p.getMinStock());
            stmt.setInt(3, p.getReservedStock());

            if (p instanceof Clothing c) {
                stmt.setString(4, "Clothing");
                stmt.setString(5, c.getSize());
                stmt.setString(6, c.getColour());
                stmt.setString(7, null);
                stmt.setString(8, null);
                stmt.setString(9, null);

            } else if (p instanceof Equipment e) {
                stmt.setString(4, "Equipment");
                stmt.setString(5, null);
                stmt.setString(6, null);
                stmt.setString(7, e.getMaterial());
                stmt.setString(8, e.getStyle());
                stmt.setString(9, null);

            } else if (p instanceof GunReplica g) {
                stmt.setString(4, "GunReplica");
                stmt.setString(5, null);
                stmt.setString(6, null);
                stmt.setString(7, g.getMaterial());
                stmt.setString(8, null);
                stmt.setString(9, g.getCalibre());

            } else {
                stmt.setString(4, "Product");
                stmt.setString(5, null);
                stmt.setString(6, null);
                stmt.setString(7, null);
                stmt.setString(8, null);
                stmt.setString(9, null);
            }

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                p.setProductNo(keys.getInt(1));
            }

            keys.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // UPDATE PRODUCT
    // ---------------------------------------------------------
    public void updateProduct(Product p) {
        try {
            String sql = "UPDATE Products SET name=?, minStock=?, reservedStock=?, type=?, size=?, colour=?, material=?, style=?, calibre=? "
                       + "WHERE productNo=?";

            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getName());
            stmt.setInt(2, p.getMinStock());
            stmt.setInt(3, p.getReservedStock());

            if (p instanceof Clothing c) {
                stmt.setString(4, "Clothing");
                stmt.setString(5, c.getSize());
                stmt.setString(6, c.getColour());
                stmt.setString(7, null);
                stmt.setString(8, null);
                stmt.setString(9, null);

            } else if (p instanceof Equipment e) {
                stmt.setString(4, "Equipment");
                stmt.setString(5, null);
                stmt.setString(6, null);
                stmt.setString(7, e.getMaterial());
                stmt.setString(8, e.getStyle());
                stmt.setString(9, null);

            } else if (p instanceof GunReplica g) {
                stmt.setString(4, "GunReplica");
                stmt.setString(5, null);
                stmt.setString(6, null);
                stmt.setString(7, g.getMaterial());
                stmt.setString(8, null);
                stmt.setString(9, g.getCalibre());

            } else {
                stmt.setString(4, "Product");
                stmt.setString(5, null);
                stmt.setString(6, null);
                stmt.setString(7, null);
                stmt.setString(8, null);
                stmt.setString(9, null);
            }

            stmt.setInt(10, p.getProductNo());

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // DELETE PRODUCT
    // ---------------------------------------------------------
    public void deleteProduct(int productNo) {
        try {
            String sql = "DELETE FROM Products WHERE productNo = ?";
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, productNo);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // BUILD PRODUCT FROM RESULTSET
    // ---------------------------------------------------------
    private Product buildProduct(ResultSet rs) throws Exception {
        String type = rs.getString("type");

        int productNo = rs.getInt("productNo");
        String name = rs.getString("name");
        int minStock = rs.getInt("minStock");
        int reservedStock = rs.getInt("reservedStock");

        switch (type) {
            case "Clothing":
                return new Clothing(
                    productNo,
                    name,
                    minStock,
                    reservedStock,
                    rs.getString("size"),
                    rs.getString("colour")
                );

            case "Equipment":
                return new Equipment(
                    productNo,
                    name,
                    minStock,
                    reservedStock,
                    rs.getString("material"),
                    rs.getString("style")
                );

            case "GunReplica":
                return new GunReplica(
                    productNo,
                    name,
                    minStock,
                    reservedStock,
                    rs.getString("material"),
                    rs.getString("calibre")
                );

            default:
                return new Product(productNo, name, minStock, reservedStock);
        }
    }
}
