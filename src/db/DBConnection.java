package db;

import java.sql.*;

public class DBConnection {

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://hildur.ucn.dk;"
                    + "databaseName=DMA-CSD-S251_10700467;user=DMA-CSD-S251_10700467;password=Password1!;encrypt=false");
            System.out.println("Connected to database: " + !con.isClosed()); 
            con.close();
            System.out.println("Connected to database: " + !con.isClosed()); 
        }    catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}