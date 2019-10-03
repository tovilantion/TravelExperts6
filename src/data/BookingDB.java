package data;

import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Booking;


import java.sql.*;

public class BookingDB {

    public static ObservableList<Booking> getBookings(){
        ObservableList<Booking>bookList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Bookings";
        try {
            //get connection
            Connection conn = ConnectionDB.getConnection();
            //create statement
            Statement statement = conn.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(sql);

            //loop through each row in resultset and create object with values from rs and add to list
            while (rs.next()){
               /* Booking booking = new Booking(
                        rs.getInt(1),
                        rs.getObject(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7));
                        while (rs.next()) {*/
            Booking book = new Booking();
            book.setBookingId(rs.getInt(1));
            book.setBookingDate(rs.getDate(2));
            book.setBookingNo(rs.getString(3));
            book.setTravelerCount(rs.getInt(4));
            book.setCustomerId(rs.getInt(5));
            book.setTripTypeId(rs.getString(6));
            book.setPackageId(rs.getInt(7));

            //Add booking to the ObservableList
            bookList.add(book);

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }



    public static int updateBooking(int bookingId, Booking book) {
        int rowsUpdated = 0;

        try {
            // get connection
            Connection connection = ConnectionDB.getConnection();
            // update query
            String updateQuery = "UPDATE BOOKINGS SET BOOKINGDATE = ?, BOOKINGNO = ?," +
                    "TRAVELERCOUNT=?, CUSTOMERID=?, TRIPTYPEID=?, PACKAGEID=? WHERE BookingID = ?";
            // prepare statement
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setObject(1, book.getBookingDate());
            statement.setString(2, book.getBookingNo());
            statement.setInt(3, book.getTravelerCount());
            statement.setInt(4, book.getCustomerId());
            statement.setString(5, book.getTripTypeId());
            statement.setInt(6, book.getPackageId());
            statement.setInt(7, bookingId);

            // execute
            rowsUpdated = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }


}
