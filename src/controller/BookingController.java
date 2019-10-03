package controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import data.BookingDB;
import data.CustomerDB;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Booking;


public class BookingController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableColumn<Booking, Integer> colBookingId;
    @FXML private TableColumn<Booking, Date> colBookingDate;
    @FXML private TableColumn<Booking, String> colBookNo;
    @FXML private TableColumn<Booking, Integer> colTravelerCount;
    @FXML private TableColumn<Booking, Integer> colCustId;
    @FXML private TableColumn<Booking, String> colTripId;
    @FXML private TableColumn<Booking, Integer> colPkgId;
    @FXML private TableView<Booking> tvBookingList;
    @FXML private Tab tabEditBooking;
    @FXML private TabPane tpBookings;


    @FXML void initialize() {



        ObservableList<Booking> bookings = BookingDB.getBookings();
        colBookingId.setCellValueFactory(cellData -> cellData.getValue().bookingIdProperty().asObject());
        colBookingDate.setCellValueFactory(cellData -> cellData.getValue().bookingDateProperty());
        colBookNo.setCellValueFactory(cellData -> cellData.getValue().bookingNoProperty());
        colTravelerCount.setCellValueFactory(cellData -> cellData.getValue().travelerCountProperty().asObject());
        colCustId.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        colTripId.setCellValueFactory(cellData -> cellData.getValue().tripTypeIdProperty());
        colPkgId.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());
        tvBookingList.setItems(bookings);


       /* colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custFirstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custLastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));
        colPostal.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPostal"));
        colHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custHomePhone"));
        colBusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custBusPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("custEmail"));
        tvCustomerList.setItems(customers);*/

        tvBookingList.setRowFactory(tv -> {
            TableRow<Booking> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Booking rowData = row.getItem();
                    //  System.out.println(rowData);  //this row was commented out
                    // action
                    tpBookings.getSelectionModel().select(tabEditBooking);
                }
            });
            return row;
        });


//         lvCustomer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


}
