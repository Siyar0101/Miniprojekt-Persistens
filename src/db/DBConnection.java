package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlserver://hildur.ucn.dk;databaseName=DMA-CSD-S251_10700467;encrypt=false";
    private static final String USER = "DMA-CSD-S251_10700467";
    private static final String PASSWORD = "Password1!";

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connected: " + !con.isClosed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

