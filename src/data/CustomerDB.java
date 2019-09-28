package data;

import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.*;

public class CustomerDB {
    public static ObservableList<Customer> getCustomers(){
        ObservableList<Customer>customers = FXCollections.observableArrayList();
            String sql = "SELECT * FROM CUSTOMERS";
            try {
                //get connection
                Connection conn = ConnectionDB.getConnection();
                //create statement
                Statement statement = conn.createStatement();
                //execute query
                ResultSet rs = statement.executeQuery(sql);

            //loop through each row in resultset and create object with values from rs and add to list
            while (rs.next()){
                Customer customer = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12));
                customers.add(customer);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    public static int updateCustomer(int customerId, Customer customer) {
        int rowsUpdated = 0;

        try {
            // get connection
            Connection connection = ConnectionDB.getConnection();
            // update query
            String updateQuery = "UPDATE CUSTOMERS SET CUSTFIRSTNAME = ?, CUSTLASTNAME = ? WHERE CUSTOMERID = ?";
            // prepare statement
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, customer.getCustFirstName());
            statement.setString(2, customer.getCustLastName());
            statement.setInt(3, customerId);

            // execute
            rowsUpdated = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }
}