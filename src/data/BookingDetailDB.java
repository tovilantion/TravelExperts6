package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookingDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class BookingDetailDB {
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
}
