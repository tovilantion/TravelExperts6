package controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import data.CustomerDB;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

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
    @FXML public ComboBox<Customer> cbCustomerId;
    @FXML public TextField tfCustomerFirstName;


    @FXML void initialize() {
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

        tvCustomerList.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Customer rowData = row.getItem();

//                    System.out.println(rowData);
                    // action
                    tpCustomers.getSelectionModel().select(tabEditCustomer);

                    tfCustomerFirstName.setText(rowData.getCustFirstName());


                }
            });
            return row;
        });


    }


}
