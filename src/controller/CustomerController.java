package controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.CustomerDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import sun.management.Agent;

public class CustomerController {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
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

    @FXML
    void onActionBtnCustomerAdd(ActionEvent event) {
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
        if (rowsUpdated == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Update failed");
            alertError.showAndWait();
        } else {
            Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Update successful");
            alertSuccess.showAndWait();
            //on update, reloads data to textfields
            loadCustomers();
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
        }
    }

    @FXML
    void initialize() {
        loadCustomers();
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
        ObservableList<Customer> customers = CustomerDB.getCustomers();
        cbCustomerId.setItems(customers);
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

    private void clearFieldsAdd() {
        tfCustFirstNameAdd.clear();
        tfCustLastNameAdd.clear();
        tfCustAddress.clear();
        tfCustCityAdd.clear();
        tfCustProvAdd.clear();
        tfCustPostalAdd.clear();
        tfCustCountryAdd.clear();
        tfCustHomePhoneAdd.clear();
        tfCustBusPhoneAdd.clear();
        tfCustEmailAdd.clear();
        tfAgentIdAdd.clear();
    }
}

