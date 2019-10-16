package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookingDetail;
import model.Product;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BookingDetailDB {
    static ObservableList<BookingDetail> getBookingDetails(int bookingId) {
        ObservableList<BookingDetail> bookingDetails = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM BookingDetails WHERE BookingId = ?";
        try {
            Connection conn = ConnectionDB.getConnection();
            //create statement
            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1,bookingId);
            //execute query
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                BookingDetail bookingDetail = new BookingDetail(rs.getInt("BookingDetailId"),
                        rs.getInt("ItineraryNo"),
                        dateFormat.format(rs.getDate("TripStart")),
                        dateFormat.format(rs.getDate("TripEnd")),
                        rs.getString("Description"),
                        rs.getString("Destination"),
                        rs.getDouble("BasePrice"),
                        rs.getDouble("AgencyCommission"),
                        rs.getInt("BookingId"),
                        rs.getString("RegionId"),
                        rs.getString("ClassId"),
                        rs.getString("FeeId"),
                        rs.getInt("ProductSupplierId"));
                bookingDetails.add(bookingDetail);
            }
        }


        catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingDetails;
    }

    public static ObservableList<BookingDetail> getBookingDetailsByBookingId(int bookingId) throws SQLException {
        ObservableList<BookingDetail> bookingdetails = FXCollections.observableArrayList();
        Connection conn = ConnectionDB.getConnection();
        String sql = "SELECT BOOKINGDETAILID, ITINERARYNO, TRIPSTART, TRIPEND, DESCRIPTION, DESTINATION, \n" +
                "BASEPRICE FROM BookingDetails WHERE BOOKINGID = " + bookingId;
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            BookingDetail bookingDetail = new BookingDetail(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getDouble(7)
            );
            bookingdetails.add(bookingDetail);
        }
        conn.close();
        return bookingdetails;
    }

}
