package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost/travelexperts";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // load driver class
            Class.forName("com.mysql.jdbc.Driver");
            // connect to the database
            conn = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return conn;
    }
}