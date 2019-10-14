package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Booking;

import java.net.URL;
import java.util.ResourceBundle;

public class BookingDisplayController implements Initializable {
    @FXML
    private Label lblBookingDate;
    @FXML
    private Label lblBookingNo;
    @FXML
    private Label lblTravelerCount;
    @FXML
    private Label lblTripType;
    @FXML
    private Label lblBookingId;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void bookingsDisplay(Booking booking) {
        lblBookingId.setText(booking.getBookingId() + "");
        lblBookingDate.setText(booking.getBookingDate().toString());
        lblBookingNo.setText(booking.getBookingNo());
        lblTravelerCount.setText(booking.getBookingId() + "");
        lblTripType.setText(booking.getTripTypeId());
    }


}
