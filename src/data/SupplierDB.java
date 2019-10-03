package data;

import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Supplier;


import java.sql.*;

public class SupplierDB {

    public static ObservableList<Supplier> getSuppliers(){
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Suppliers";
        try {
            //get connection
            Connection conn = ConnectionDB.getConnection();
            //create statement
            Statement statement = conn.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(sql);

            //loop through each row in resultset and create object with values from rs and add to list
            while (rs.next()){
               /* Supplier supplier = new Supplier(
                        rs.getInt(1),
                        rs.getObject(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7));
                        while (rs.next()) {*/
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getInt(1));
                supplier.setSupName(rs.getString(2));


                //Add booking to the ObservableList
                supplierList.add(supplier);

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }



    public static int updateBooking(int supplierId, Supplier supplier) {
        int rowsUpdated = 0;

        try {
            // get connection
            Connection connection = ConnectionDB.getConnection();
            // update query
            String updateQuery = "UPDATE SUPPLIERS SET SUPNAME = ?, WHERE SUPPLIERID = ?";
            // prepare statement
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, supplier.getSupName());


            // execute
            rowsUpdated = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }


}

