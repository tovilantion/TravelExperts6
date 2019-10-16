package data;

//import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import model.Booking;
import model.Supplier;


import java.sql.*;

public class SupplierDB {

    public static ObservableList<Supplier> getSuppliers(){
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM SUPPLIERS";
        try {
            //get connection
            Connection conn = ConnectionDB.getConnection();
            //create statement
            Statement statement = conn.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(sql);

            //loop through each row in resultset and create object with values from rs and add to list
            while (rs.next()){
                Supplier supplier = new Supplier(
                        rs.getInt(1),
                        rs.getString(2));

                      //  while (rs.next()) {
               /* Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getInt(1));
                supplier.setSupName(rs.getString(2));*/


                //Add supplier to the ObservableList
                suppliers.add(supplier);

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }



    public static int updateSupplier(int supplierId, Supplier supplier) {
        int rowsUpdated = 0;

        try {
            // get connection
            Connection connection = ConnectionDB.getConnection();
            // update query
            String updateQuery = "UPDATE SUPPLIERS SET SUPNAME = ? WHERE SUPPLIERID = ?";
            // prepare statement
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, supplier.getSupName());
            statement.setInt(2, supplierId);

            // execute
            rowsUpdated = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }
    public static int deleteSupplier(int supplierId){
        int rowsDeleted = 0;

        try {
            Connection connection = ConnectionDB.getConnection();
            //delete query
            String deleteQuery = "DELETE FROM `suppliers` WHERE SupplierId=?";

            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, supplierId);
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;

    }

    public static int addSupplier(Supplier supplier){
        int rowsInserted = 0;
        try {
            Connection connection = ConnectionDB.getConnection();
            String insertQuery = "INSERT INTO `suppliers`(`SupName`) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, supplier.getSupName());


            rowsInserted =statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }



}



