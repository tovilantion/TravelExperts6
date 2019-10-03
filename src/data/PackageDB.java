package data;
//import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;
import model.Package;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PackageDB {
    public static ObservableList<Package> getPackages() {
        ObservableList<Package> packages = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Packages";
        try {
            //get connection
            Connection conn = ConnectionDB.getConnection();
            //create statement
            Statement statement = conn.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(sql);

            //loop through each row in resultset and create object with values from rs and add to list
            while (rs.next()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                Package tempPackage = new Package(
                        rs.getInt("PackageId"),
                        rs.getString("PkgName"),
                        dateFormat.format(rs.getDate("PkgStartDate")),
                        dateFormat.format(rs.getDate("PkgEndDate")),
                        rs.getString("PkgDesc"),
                        rs.getDouble("PkgBasePrice"),
                        rs.getDouble("PkgAgencyCommission"));

                packages.add(tempPackage);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
}
