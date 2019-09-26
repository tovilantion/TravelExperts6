package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static final String CONNECTION_STRING = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=TravelExperts;integratedSecurity=true;";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // load driver class
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // connect to the database
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return conn;
    }
}