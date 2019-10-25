package controller;

import java.awt.peer.ButtonPeer;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import data.BookingDB;
import data.CustomerDB;
import data.CustomerValidation;
import data.DataValidation;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Booking;
import model.Customer;

public class CustomerController {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Tab tabCustomerList;
    @FXML private TableColumn<Customer, Integer> colCustomerId;
    @FXML private TableColumn<Customer, String> colCustFirstName;
    @FXML private TableColumn<Customer, String> colLastName;
    @FXML private TableColumn<Customer, String> colAddress;
    @FXML private TableColumn<Customer, String> colPostal;
    @FXML private TableColumn<Customer, String> colHomePhone;
    @FXML private TableColumn<Customer, String> colBusPhone;
    @FXML private TableColumn<Customer, String> colEmail;
    @FXML private TableView<Customer> tvCustomerList;
    @FXML private Tab tabEditCustomer;
    @FXML private TabPane tpCustomers;
    @FXML private ComboBox<Customer> cbCustomerId;
    @FXML private TextField tfCustFirstName;
    @FXML private TextField tfCustLastName;
    @FXML private TextField tfCustAddress;
    @FXML private TextField tfCustCity;
    @FXML private TextField tfCustProv;
    @FXML private TextField tfCustPostal;
    @FXML private TextField tfCustCountry;
    @FXML private TextField tfCustHomePhone;
    @FXML private TextField tfCustBusPhone;
    @FXML private TextField tfCustEmail;
    @FXML private Tab tabAddCustomer;
    @FXML private TextField tfCustFirstNameAdd;
    @FXML private TextField tfCustLastNameAdd;
    @FXML private TextField tfCustAddressAdd;
    @FXML private TextField tfCustCityAdd;
    @FXML private TextField tfCustProvAdd;
    @FXML private TextField tfCustPostalAdd;
    @FXML private TextField tfCustCountryAdd;
    @FXML private TextField tfCustHomePhoneAdd;
    @FXML private TextField tfCustBusPhoneAdd;
    @FXML private TextField tfCustEmailAdd;
    @FXML private TextField tfAgentIdAdd;
    @FXML private Button btnAdd;
    @FXML private TextField tfFilter;
    @FXML private TableView<Booking> tvBooking;
    @FXML private TableColumn<Booking, Integer> colBookingId;
    @FXML private TableColumn<Booking, LocalDate> colBookingDate;
    @FXML private TableColumn<Booking, String> colBookingNo;
    @FXML private TableColumn<Booking, Integer> colTravelerCount;
    @FXML private TableColumn<Booking, Integer> customerId;
    @FXML private TableColumn<Booking, String> colTripTypeId;
    @FXML private TableColumn<Booking, Integer> colPackageId;

    @FXML
    void onActionTfFilter(ActionEvent event) {

    }

    @FXML
    void onActionBtnCustomerAdd(ActionEvent event) {
        if (IsValidDataForCustomerAdd()) {
            // confirm to update
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to proceed?",
                    ButtonType.YES, ButtonType.CANCEL);

            Optional<ButtonType> result = confirm.showAndWait();

            if(result.get() == ButtonType.YES) {
                Customer customer = new Customer(
                        tfCustFirstNameAdd.getText(),
                        tfCustLastNameAdd.getText(),
                        tfCustAddressAdd.getText(),
                        tfCustCityAdd.getText(),
                        tfCustProvAdd.getText(),
                        tfCustPostalAdd.getText(),
                        tfCustCountryAdd.getText(),
                        tfCustHomePhoneAdd.getText(),
                        tfCustBusPhoneAdd.getText(),
                        tfCustEmailAdd.getText(),
                        Integer.valueOf(tfAgentIdAdd.getText()));
                int rowsInserted = CustomerDB.addCustomer(customer);
                if (rowsInserted > 0) {
                    tpCustomers.getSelectionModel().select(tabCustomerList);
                    loadCustomers();

//                    Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Insert successful");
//                    alertSuccess.showAndWait();
                }
            }
        }
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {
        //TODO: pass delete bookings in deletecustomer method to delete both customer and customer bookings (constraint)
        int customerId = cbCustomerId.getSelectionModel().getSelectedItem().getCustomerId();
        int rowsDeleted = CustomerDB.deleteCustomer(customerId);
        if (rowsDeleted == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Delete failed");
            alertError.showAndWait();
        } else {

            Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Delete successful");
            alertSuccess.showAndWait();
            loadCustomers();
            clearFields();
        }
    }

    @FXML
    void onActionBtnEdit(ActionEvent event) {
        enableTextFields();
    }

    //Save customer
    @FXML
    void onActionBtnSave(ActionEvent event) {
        if(IsValidDataForCustomerEdit()) {
            // confirm to update
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to proceed?",
                    ButtonType.YES, ButtonType.CANCEL);

            Optional<ButtonType> result = confirm.showAndWait();

            if(result.get() == ButtonType.YES) {
                int customerId = cbCustomerId.getSelectionModel().getSelectedItem().getCustomerId();
                Customer customer = new Customer(
                        tfCustFirstName.getText(),
                        tfCustLastName.getText(),
                        tfCustAddress.getText(),
                        tfCustCity.getText(),
                        tfCustProv.getText(),
                        tfCustPostal.getText(),
                        tfCustCountry.getText(),
                        tfCustHomePhone.getText(),
                        tfCustBusPhone.getText(),
                        tfCustEmail.getText());
                int rowsUpdated = CustomerDB.updateCustomer(customerId, customer);
                if (rowsUpdated > 0) {
                    tpCustomers.getSelectionModel().select(tabCustomerList);
                    loadCustomers();
                }
            }
        }

    }

    @FXML
    void onActionCbCustomerId(ActionEvent event) {
        // get customer id
        Customer selectedCustomer = cbCustomerId.getSelectionModel().getSelectedItem();
        // fill text fields
        if (selectedCustomer != null) {
            tfCustFirstName.setText(selectedCustomer.getCustFirstName());
            tfCustLastName.setText(selectedCustomer.getCustLastName());
            tfCustAddress.setText(selectedCustomer.getCustAddress());
            tfCustCity.setText(selectedCustomer.getCustCity());
            tfCustProv.setText(selectedCustomer.getCustProv());
            tfCustPostal.setText(selectedCustomer.getCustPostal());
            tfCustCountry.setText(selectedCustomer.getCustCountry());
            tfCustHomePhone.setText(selectedCustomer.getCustHomePhone());
            tfCustBusPhone.setText(selectedCustomer.getCustBusPhone());
            tfCustEmail.setText(selectedCustomer.getCustEmail());

            if(selectedCustomer != null) {
                int selectedCustId = cbCustomerId.getSelectionModel().getSelectedItem().getCustomerId();
                ObservableList<Booking> bookings = BookingDB.getBookingByCustomerId(selectedCustId);
                tvBooking.setItems(bookings);
            }


        }
    }

    @FXML
    void initialize() {

        //load data in customer list table
        ObservableList<Customer> customers = CustomerDB.getCustomers();
        colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custFirstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custLastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));
        colPostal.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPostal"));
        colHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custHomePhone"));
        colBusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custBusPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("custEmail"));
        tvCustomerList.setItems(customers);

        //load data in booking table
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colBookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        colBookingNo.setCellValueFactory(new PropertyValueFactory<>("bookingNo"));
        colTravelerCount.setCellValueFactory(new PropertyValueFactory<>("travelerCount"));
        //colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTripTypeId.setCellValueFactory(new PropertyValueFactory<>("tripTypeId"));
        //colPackageId.setCellValueFactory(new PropertyValueFactory<>("packageId"));
        loadCustomers();

        //filter data
        filterTable(customers);

        //on cell double click, redirects to edit customer tab and display selected customer data
        tvCustomerList.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Customer rowData = row.getItem();
                    tpCustomers.getSelectionModel().select(tabEditCustomer);
                    cbCustomerId.getSelectionModel().select(rowData);
                }
            });
            return row;
        });


    }

    //load data in edit customer combo box
    private void loadCustomers() {
        ObservableList<Customer> customersCB = CustomerDB.getCustomers();
        cbCustomerId.setItems(customersCB);

        ObservableList<Customer> customers = CustomerDB.getCustomers();
        colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custFirstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custLastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));
        colPostal.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPostal"));
        colHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custHomePhone"));
        colBusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custBusPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("custEmail"));
        tvCustomerList.setItems(customers);
    }

    //enables text fields
    private void enableTextFields() {
        tfCustFirstName.setDisable(false);
        tfCustLastName.setDisable(false);
        tfCustAddress.setDisable(false);
        tfCustCity.setDisable(false);
        tfCustProv.setDisable(false);
        tfCustPostal.setDisable(false);
        tfCustCountry.setDisable(false);
        tfCustHomePhone.setDisable(false);
        tfCustBusPhone.setDisable(false);
        tfCustEmail.setDisable(false);

        tfCustFirstName.setEditable(true);
        tfCustLastName.setEditable(true);
        tfCustAddress.setEditable(true);
        tfCustCity.setEditable(true);
        tfCustProv.setEditable(true);
        tfCustPostal.setEditable(true);
        tfCustCountry.setEditable(true);
        tfCustHomePhone.setEditable(true);
        tfCustBusPhone.setEditable(true);
        tfCustEmail.setEditable(true);
    }

    private void clearFields() {
        tfCustFirstName.clear();
        tfCustLastName.clear();
        tfCustAddress.clear();
        tfCustCity.clear();
        tfCustProv.clear();
        tfCustPostal.clear();
        tfCustCountry.clear();
        tfCustHomePhone.clear();
        tfCustBusPhone.clear();
        tfCustEmail.clear();
    }

    private void filterTable(ObservableList<Customer> customers) {
        FilteredList<Customer> filteredData = new FilteredList<>(customers, p -> true);
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> {
                // If filter text is empty, display all customers.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //ignores letter case
                String lowerCaseFilter = newValue.toLowerCase();

                if (customer.getCustFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (customer.getCustLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        //wrap filteredlist in a sorted list
        SortedList<Customer> sortedData = new SortedList<>(filteredData);
        //bind sortedlist comparator to the tableview comparator
        sortedData.comparatorProperty().bind(tvCustomerList.comparatorProperty());
        //add sorted data to the table
        tvCustomerList.setItems(sortedData);
    }

    private boolean IsValidDataForCustomerEdit(){
        return
                DataValidation.IsPresent(tfCustFirstName, "First Name") &&
                        DataValidation.IsPresent(tfCustLastName, "Last Name") &&
                        DataValidation.IsPresent(tfCustAddress, "Address") &&
                        DataValidation.IsPresent(tfCustCity, "City") &&
                        DataValidation.IsPresent(tfCustProv, "Province") &&
                        DataValidation.IsPresent(tfCustPostal, "Postal Code") &&
                        DataValidation.IsPresent(tfCustCountry, "Country") &&
                        DataValidation.IsPresent(tfCustHomePhone, "Home Phone") &&
                        DataValidation.IsPresent(tfCustBusPhone, "Business Phone") &&
                        DataValidation.IsPresent(tfCustEmail, "Email") &&
                        CustomerValidation.emailFormat(tfCustEmail, "Email");
    }

    private boolean IsValidDataForCustomerAdd(){
        return
                DataValidation.IsPresent(tfCustFirstNameAdd, "First Name") &&
                        DataValidation.IsPresent(tfCustLastNameAdd, "Last Name") &&
                        DataValidation.IsPresent(tfCustAddressAdd, "Address") &&
                        DataValidation.IsPresent(tfCustCityAdd, "City") &&
                        DataValidation.IsPresent(tfCustProvAdd, "Province") &&
                        DataValidation.IsPresent(tfCustPostalAdd, "Postal Code") &&
                        DataValidation.IsPresent(tfCustCountryAdd, "Country") &&
                        DataValidation.IsPresent(tfCustHomePhoneAdd, "Home Phone") &&
                        DataValidation.IsPresent(tfCustBusPhoneAdd, "Business Phone") &&
                        DataValidation.IsPresent(tfCustEmailAdd, "Email");
    }


}

