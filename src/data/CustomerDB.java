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
            String updateQuery = "UPDATE `customers` SET `CustFirstName`=?," +
                    "`CustLastName`=?,`CustAddress`=?,`CustCity`=?," +
                    "`CustProv`=?,`CustPostal`=?,`CustCountry`=?," +
                    "`CustHomePhone`=?,`CustBusPhone`=?,`CustEmail`=? " +
                    "WHERE CustomerId=?";
            // prepare statement
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, customer.getCustFirstName());
            statement.setString(2, customer.getCustLastName());
            statement.setString(3,customer.getCustAddress());
            statement.setString(4,customer.getCustCity());
            statement.setString(5,customer.getCustProv());
            statement.setString(6,customer.getCustPostal());
            statement.setString(7,customer.getCustCountry());
            statement.setString(8,customer.getCustHomePhone());
            statement.setString(9,customer.getCustBusPhone());
            statement.setString(10,customer.getCustEmail());
            statement.setInt(11,customerId);

            // execute
            rowsUpdated = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    public static int deleteCustomer(int customerId){
        int rowsDeleted = 0;

        try {
            Connection connection = ConnectionDB.getConnection();
            //delete query
            String deleteQuery = "DELETE FROM `customers` WHERE CustomerId=?";

            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, customerId);
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;

    }

    public static int addCustomer(Customer customer){
        int rowsInserted = 0;
        try {
            Connection connection = ConnectionDB.getConnection();
            String insertQuery = "INSERT INTO `customers`(`CustFirstName`, `CustLastName`, " +
                    "`CustAddress`, `CustCity`, `CustProv`, `CustPostal`, `CustCountry`, `CustHomePhone`, " +
                    "`CustBusPhone`, `CustEmail`,`AgentId`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, customer.getCustFirstName());
            statement.setString(2, customer.getCustLastName());
            statement.setString(3,customer.getCustAddress());
            statement.setString(4,customer.getCustCity());
            statement.setString(5,customer.getCustProv());
            statement.setString(6,customer.getCustPostal());
            statement.setString(7,customer.getCustCountry());
            statement.setString(8,customer.getCustHomePhone());
            statement.setString(9,customer.getCustBusPhone());
            statement.setString(10,customer.getCustEmail());
            statement.setInt(11,customer.getAgentId());

            rowsInserted =statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }

}