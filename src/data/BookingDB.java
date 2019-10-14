package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Booking;


import java.sql.*;

public class BookingDB {

    public static ObservableList<Booking> getBookings() {
        ObservableList<Booking> bookList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Bookings";
        try {
            //get connection
            Connection conn = ConnectionDB.getConnection();
            //create statement
            Statement statement = conn.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(sql);

            //loop through each row in resultset and create object with values from rs and add to list
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7));
                        /*while (rs.next()) {
            Booking book = new Booking();
            book.setBookingId(rs.getInt(1));
            book.setBookingDate(rs.getDate(2));
            book.setBookingNo(rs.getString(3));
            book.setTravelerCount(rs.getInt(4));
            book.setCustomerId(rs.getInt(5));
            book.setTripTypeId(rs.getString(6));
            book.setPackageId(rs.getInt(7));
*/
                //Add booking to the ObservableList
                bookList.add(booking);

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

    public static int deleteBooking(int bookingId) {
        int rowsDeleted = 0;

        try {
            Connection connection = ConnectionDB.getConnection();
            //delete query
            String deleteQuery = "DELETE FROM `bookings` WHERE BookingId=?";  //this is original query
            //   "DELETE  `bookings`, `bookingdetails` FROM `bookings` INNER JOIN `bookingdetails` ON `bookings`.`BookingId` = `bookingdetails`.`BookingId` WHERE `bookings`.`BookingId`= ?";
/*            Statement st = connection.createStatement();
            st.addBatch("DELETE FROM `bookingdetails` WHERE BookingId=?");
            st.addBatch("DELETE FROM `bookings` WHERE BookingId=?");*/

            //   int[] results = st.executeBatch();
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, bookingId);
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;

    }

    public static int addBooking(Booking booking) {
        int rowsInserted = 0;
        try {
            Connection connection = ConnectionDB.getConnection();
            String insertQuery = "INSERT INTO `bookings`(`BookingDate`, `BookingNo`, " +
                    "`TravelerCount`, `CustomerId`, `TripTypeId`, `PackageId`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setObject(1, booking.getBookingDate());
            statement.setString(2, booking.getBookingNo());
            statement.setInt(3, booking.getTravelerCount());
            statement.setInt(4, booking.getCustomerId());
            statement.setString(5, booking.getTripTypeId());
            statement.setInt(6, booking.getPackageId());


            rowsInserted = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }

    //get customer booking id and display in edit customer tab
    public static ObservableList<Booking> getBookingByCustomerId(int customerId) {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();

        try {
            // load driver
            Class.forName("com.mysql.jdbc.Driver");
            // get connection
            Connection connection = ConnectionDB.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // select query
            String selectQuery = "SELECT * FROM BOOKINGS WHERE CUSTOMERID = " + customerId;


            // execute
            ResultSet rs = statement.executeQuery(selectQuery);
            // create new object
            while (rs.next()) {
                Booking book = new Booking();
                book.setBookingId(rs.getInt(1));
                book.setBookingDate(rs.getDate(2));
                book.setBookingNo(rs.getString(3));
                book.setTravelerCount(rs.getInt(4));
                book.setCustomerId(rs.getInt(5));
                book.setTripTypeId(rs.getString(6));
                book.setPackageId(rs.getInt(7));
                bookings.add(book);
            }
            // close connection
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return bookings;
    }

}
