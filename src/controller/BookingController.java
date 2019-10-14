package controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import data.BookingDB;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Booking;
import model.Package;


public class BookingController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableColumn<Booking, Integer> colBookingId;
    @FXML private TableColumn<Booking, String> colBookingDate;
    @FXML private TableColumn<Booking, String> colBookNo;
    @FXML private TableColumn<Booking, Integer> colTravelerCount;
    @FXML private TableColumn<Booking, Integer> colCustId;
    @FXML private TableColumn<Booking, String> colTripId;
    @FXML private TableColumn<Booking, Integer> colPkgId;
    @FXML private TableView<Booking> tvBookingList;
    @FXML private Tab tabEditBooking;
    @FXML private TabPane tpBookings;
    @FXML private ComboBox<Booking> cbBookingID;
  //  @FXML private TextField tfBookingDate;
    @FXML private DatePicker dpBookingDate;
    @FXML private TextField tfBookingNo;
    @FXML private TextField tfTravelerCount;
    @FXML private TextField tfCustId;
    @FXML private TextField tfTripId;
    @FXML private TextField tfPkgId;
    @FXML private Tab tabAddBooking;
  //  @FXML private TextField tfBookingDateAdd;
    @FXML private DatePicker dpBookingDateAdd;
    @FXML private TextField tfBookingNoAdd;
    @FXML private TextField tfTravelerCountAdd;
    @FXML private TextField tfCustIdAdd;
    @FXML private TextField tfTripIdAdd;
    @FXML private TextField tfPkgIdAdd;
    @FXML private TextField tfBookingIdAdd;
    @FXML private Button btnAdd;



    @FXML
    void onActionBtnBookingAdd(ActionEvent event) {
        Booking booking = new Booking(
              //  Integer.parseInt(tfBookingIdAdd.getText()),
                dpBookingDateAdd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
              //  (dpBookingDateAdd.Date.valueOf()),  //was Date.valueOf.
                tfBookingNoAdd.getText(),
                Integer.parseInt(tfTravelerCountAdd.getText()),
                Integer.parseInt(tfCustIdAdd.getText()),
                tfTripIdAdd.getText(),
                Integer.parseInt(tfPkgIdAdd.getText()));
              //  Integer.valueOf(tfBookingIdAdd.getText()));
        int rowsInserted = BookingDB.addBooking(booking);
        if (rowsInserted == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Failed to add customer");
            alertError.showAndWait();
        } else {
            Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Insert successful");
            alertSuccess.showAndWait();
        }
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {
        //TODO: pass delete bookings in deletecustomer method to delete both customer and customer bookings (constraint)
        int bookingId = cbBookingID.getSelectionModel().getSelectedItem().getBookingId();
        int rowsDeleted = BookingDB.deleteBooking(bookingId);
        if (rowsDeleted == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Delete failed");
            alertError.showAndWait();
        } else {

            Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Delete successful");
            alertSuccess.showAndWait();
            loadBookings();
            clearFields();
        }
    }

    @FXML
    void onActionBtnEdit(ActionEvent event) {
        //Set initial textfield status
     //   initializeFieldState(false);
        enableTextFields();
    }



    //Save booking
    @FXML
    void onActionBtnSave(ActionEvent event) {
        //Set fields to disabled again
      //  initializeFieldState(true);

        int bookingId = cbBookingID.getSelectionModel().getSelectedItem().getBookingId();
        Booking booking = new Booking(
                dpBookingDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                tfBookingNo.getText(),
                Integer.parseInt(tfTravelerCount.getText()),
                Integer.parseInt(tfCustId.getText()),
                tfTripId.getText(),
                Integer.parseInt(tfPkgId.getText()));

        int rowsUpdated = BookingDB.updateBooking(bookingId, booking);
        if (rowsUpdated == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Update failed");
            alertError.showAndWait();
        } else {
            Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Update successful");
            alertSuccess.showAndWait();
            //on update, reloads data to textfields
            loadBookings();
        }

    }

/*    @FXML
    void onActionCbBookingId(ActionEvent event) {
        // get booking id
        Booking selectedBooking = cbBookingID.getSelectionModel().getSelectedItem();
        // fill text fields
        if (selectedBooking != null) {
            tfBookingDate.setText((String) selectedBooking.getBookingDate());
            tfBookingNo.setText(selectedBooking.getBookingNo());
            tfTravelerCount.setText(String.valueOf(selectedBooking.getTravelerCount()));
            tfCustId.setText(String.valueOf(selectedBooking.getCustomerId()));
            tfTripId.setText(String.valueOf(selectedBooking.getTripTypeId()));
            tfPkgId.setText(String.valueOf(selectedBooking.getPackageId()));


        }
    }*/
    public void onActionCbBookingId(){ //on click method for ComboBox was loadComboBox  Darren Method
        Booking selectedBooking = (Booking)cbBookingID.getSelectionModel().getSelectedItem();
        cbBookingID.setValue(selectedBooking);
      //  tfPkgName.setText(selectedPackage.getPkgName());
        //Set dates
        try {
            java.util.Date dDate = new SimpleDateFormat("yyyy-MM-dd").parse(selectedBooking.getBookingDate()); // This throws a ParseException
           // java.util.Date dEnd = new SimpleDateFormat("yyyy-MM-dd").parse(selectedPackage.getPkgEndDate());
            dpBookingDate.setValue(dDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        //    dpBookingDate.setText(String.valueOf(dDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
         //  dpPkgEndDate.setValue(dEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tfBookingNo.setText(selectedBooking.getBookingNo());
        tfTravelerCount.setText(String.valueOf(selectedBooking.getTravelerCount()));
        tfCustId.setText(String.valueOf(selectedBooking.getCustomerId()));
        tfTripId.setText(String.valueOf(selectedBooking.getTripTypeId()));
        tfPkgId.setText(String.valueOf(selectedBooking.getPackageId()));
        //taPkgDesc.setText(selectedPackage.getPkgDesc());

    }//end loadComboBox
    @FXML void initialize() {

        loadBookings();

        ObservableList<Booking> bookings = BookingDB.getBookings();
        colBookingId.setCellValueFactory(cellData -> cellData.getValue().bookingIdProperty().asObject());
        colBookingDate.setCellValueFactory(cellData -> cellData.getValue().bookingDateProperty());
        colBookNo.setCellValueFactory(cellData -> cellData.getValue().bookingNoProperty());
        colTravelerCount.setCellValueFactory(cellData -> cellData.getValue().travelerCountProperty().asObject());
        colCustId.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        colTripId.setCellValueFactory(cellData -> cellData.getValue().tripTypeIdProperty());
        colPkgId.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());
        tvBookingList.setItems(bookings);

        tvBookingList.setRowFactory(tv -> {
            TableRow<Booking> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Booking rowData = row.getItem();
                    //  System.out.println(rowData);  //this row was commented out
                    // action
                    tpBookings.getSelectionModel().select(tabEditBooking);
                    cbBookingID.getSelectionModel().select(rowData);
                }
            });
            return row;
        });
        //Set initial textfield status
       // initializeFieldState(true);
    }

    //load data in edit booking combo box
    private void loadBookings() {
        ObservableList<Booking> bookings = BookingDB.getBookings();
        cbBookingID.setItems(bookings);
    }

    //enables text fields
    private void enableTextFields() {
        dpBookingDate.setDisable(false);
      //  tfBookingDate.setDisable(false);
        tfBookingNo.setDisable(false);
        tfTravelerCount.setDisable(false);
        tfCustId.setDisable(false);
        tfTripId.setDisable(false);
        tfPkgId.setDisable(false);

        dpBookingDate.setEditable(true);
      // tfBookingDate.setEditable(true);
        tfBookingNo.setEditable(true);
        tfTravelerCount.setEditable(true);
        tfCustId.setEditable(true);
        tfTripId.setEditable(true);
        tfPkgId.setEditable(true);
    }

    private void clearFields() {
        dpBookingDate.setValue(null);
        //tfBookingDate.clear();
        tfBookingNo.clear();
        tfTravelerCount.clear();
        tfCustId.clear();
        tfTripId.clear();
        tfPkgId.clear();
    }

    private void clearFieldsAdd() {
        dpBookingDate.setValue(null);
       // tfBookingDateAdd.clear();
        tfBookingNoAdd.clear();
        tfTravelerCountAdd.clear();
        tfCustIdAdd.clear();
        tfTripIdAdd.clear();
        tfPkgIdAdd.clear();
    }

 /*   public void initializeFieldState(boolean status){
        //Set initial textfield status
        dpBookingDate.setDisable(status);
        tfBookingNo.setDisable(status);
        tfTravelerCount.setDisable(status);
        tfCustId.setDisable(status);
        tfTripId.setDisable(status);
        tfPkgId.setDisable(status);

    }*/
}


