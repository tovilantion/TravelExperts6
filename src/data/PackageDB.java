package data;
//import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Customer;
import model.Package;
import model.Supplier;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
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

            //loop through each row in result set and create object with values from rs and add to list
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

    //Method to load package when ComboBox is chosen
    public static Package loadPackageById(Integer PackageId){
        String sql = "SELECT * FROM Packages WHERE PackageId = " + PackageId;
        Package packageById = null;
        try {
            //get connection
            Connection conn = ConnectionDB.getConnection();
            //create statement
            Statement statement = conn.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(sql);

            //loop through each row in resultset and create object with values from rs and add to list
            while (rs.next()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Package tempPackage = new Package(
                        rs.getInt("PackageId"),
                        rs.getString("PkgName"),
                        dateFormat.format(rs.getDate("PkgStartDate")),
                        dateFormat.format(rs.getDate("PkgEndDate")),
                        rs.getString("PkgDesc"),
                        rs.getDouble("PkgBasePrice"),
                        rs.getDouble("PkgAgencyCommission"));
                packageById = tempPackage;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageById;
    }

    public static void editPackage(Package oldPackage) throws SQLException, ParseException {


        Connection con = ConnectionDB.getConnection();

        String sql = "UPDATE Packages SET PkgName = ?, PkgStartDate = ?, PkgEndDate = ?, PkgBasePrice = ?,"+
                " PkgAgencyCommission = ?, PkgDesc = ? WHERE PackageId = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        //Convert dates to SQL Date
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateStart = sdf1.parse(oldPackage.getPkgStartDate());
        Date sqlStartDate = new java.sql.Date(dateStart.getTime());

        java.util.Date dateEnd = sdf1.parse(oldPackage.getPkgEndDate());
        Date sqlEndDate = new java.sql.Date(dateEnd.getTime());


        stmt.setString(1, oldPackage.getPkgName());
        stmt.setDate(2, sqlStartDate);
        stmt.setDate(3, sqlEndDate);
        stmt.setDouble(4, oldPackage.getPkgBasePrice());
        stmt.setDouble(5, oldPackage.getPkgAgencyCommission());
        stmt.setString(6, oldPackage.getPkgDesc());
        stmt.setInt(7, oldPackage.getPackageId());

        int rowsAffected = stmt.executeUpdate();

        //Display confirmation message
        if (rowsAffected == 0){
            System.out.println("Update failed");
            //Alert not working for some reason..
            Alert alert = new Alert(Alert.AlertType.WARNING, "Data Saved", ButtonType.OK);
            alert.showAndWait();
        }else{
            System.out.println("Update success!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Saved", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public static void insertPackage(Package newPackage){
        Connection con = ConnectionDB.getConnection();

        //giving me an error for some reason - victor
        //String sql = "INSERT INTO Packages VALUES(?, ?, ?, ?, ?, ?)";
        //PkgName, PkgStartDate, PkgEndDate, PkgDesc, PkgBasePrice, PkgAgencyCommission

        //replaced with this
        String sql = "INSERT INTO `packages`(`PkgName`, `PkgStartDate`, `PkgEndDate`," +
                " `PkgDesc`, `PkgBasePrice`, `PkgAgencyCommission`)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            //Convert dates to SQL Date
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateStart = sdf1.parse(newPackage.getPkgStartDate());
            Date sqlStartDate = new java.sql.Date(dateStart.getTime());

            java.util.Date dateEnd = sdf1.parse(newPackage.getPkgEndDate());
            Date sqlEndDate = new java.sql.Date(dateEnd.getTime());


            stmt.setString(1, newPackage.getPkgName());
            stmt.setDate(2, sqlStartDate);
            stmt.setDate(3, sqlEndDate);
            stmt.setString(4, newPackage.getPkgDesc());
            stmt.setBigDecimal(5, BigDecimal.valueOf(newPackage.getPkgBasePrice()));
            stmt.setBigDecimal(6, BigDecimal.valueOf(newPackage.getPkgAgencyCommission()));


            int rowsAffected = stmt.executeUpdate();

            //Display confirmation message
            if (rowsAffected == 0){
                System.out.println("Update failed");
                //Alert not working for some reason..
                Alert alert = new Alert(Alert.AlertType.WARNING, "Update failed", ButtonType.OK);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Saved", ButtonType.OK);
                alert.showAndWait();
                System.out.println("Update success!");
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

    }

}
