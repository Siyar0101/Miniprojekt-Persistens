package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection utility class.
 * 
 * This class manages the connection to the SQL Server database.
 * It provides a method to obtain a new database connection.
 * 
 * @author Andreas Larsen, Magnus Remmer, Benyamin Mannan, Said Hamidi, Siyar Ustun
 * @version 1.0
 */
public class DBConnection {

    private static final String URL = "jdbc:sqlserver://hildur.ucn.dk;databaseName=DMA-CSD-S251_10700467;encrypt=false";
    private static final String USER = "DMA-CSD-S251_10700467";
    private static final String PASSWORD = "Password1!";

    /**
     * Main method for testing database connectivity.
     * 
     * @param args command line arguments (not used)
     */
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

    /**
     * Gets a new connection to the database.
     * 
     * @return a Connection object to the database
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

