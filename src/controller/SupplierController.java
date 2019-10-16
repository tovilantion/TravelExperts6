package controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import data.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import model.*;
import model.Package;


public class SupplierController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableColumn<Supplier, Integer> colSupplierId;
    @FXML
    private TableColumn<Supplier, String> colSupName;
    @FXML
    private TableView<Supplier> tvSupplierList;
    @FXML
    private Tab tabEditSupplier;
    @FXML
    private TabPane tpSuppliers;
    @FXML
    private ComboBox<Supplier> cbSupplierId;
    @FXML
    private Tab tabAddSupplier;
    @FXML
    private TextField tfSupplierId;
    @FXML
    private TextField tfSupName;
    @FXML
    private TextField tfSupplierIdAdd;
    @FXML
    private TextField tfSupNameAdd;
    @FXML
    private TableView<Product> tvProducts;
    @FXML
    private TableColumn<Product, Integer> colProductId;
    @FXML
    private TableColumn<Product, String> colProductName;

    @FXML
    void onActionBtnSupplierAdd(ActionEvent event) {
        Supplier supplier = new Supplier(
                tfSupName.getText());

        int rowsInserted = SupplierDB.addSupplier(supplier);
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
        int supplierId = cbSupplierId.getSelectionModel().getSelectedItem().getSupplierId();
        int rowsDeleted = SupplierDB.deleteSupplier(supplierId);
        if (rowsDeleted == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Delete failed");
            alertError.showAndWait();
        } else {

            Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Delete successful");
            alertSuccess.showAndWait();
            loadSuppliers();
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
        int supplierId = cbSupplierId.getSelectionModel().getSelectedItem().getSupplierId();
        Supplier supplier = new Supplier(
                tfSupName.getText());

        int rowsUpdated = SupplierDB.updateSupplier(supplierId, supplier);
        if (rowsUpdated == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Update failed");
            alertError.showAndWait();
        } else {
            Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Update successful");
            alertSuccess.showAndWait();
            //on update, reloads data to textfields
            loadSuppliers();
        }

    }

    @FXML
    void onActionCbSupplierId(ActionEvent event) throws SQLException {
        colProductId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        colProductName.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());
        // get supplier id
        Supplier selectedSupplier = cbSupplierId.getSelectionModel().getSelectedItem();
        // fill text fields
        if (selectedSupplier != null) {
            tfSupName.setText(selectedSupplier.getSupName());
            //fill product table view
            int selectedCustId = cbSupplierId.getSelectionModel().getSelectedItem().getSupplierId();
            ObservableList<Product> products = ProductDB.getProductsBySupplierId(selectedCustId);
            tvProducts.setItems(products);
        }
    }
//    int selectedCustId = cbCustomerId.getSelectionModel().getSelectedItem().getCustomerId();
//    ObservableList<Booking> bookings = BookingDB.getBookingByCustomerId(selectedCustId);
//            tvBooking.setItems(bookings);


    @FXML
    void initialize() {

        loadSuppliers();

        ObservableList<Supplier> suppliers = SupplierDB.getSuppliers();
        colSupplierId.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty().asObject());
        colSupName.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());



        tvSupplierList.setItems(suppliers);

        tvSupplierList.setRowFactory(tv -> {
            TableRow<Supplier> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Supplier rowData = row.getItem();


                    // action
                    tpSuppliers.getSelectionModel().select(tabEditSupplier);
                    cbSupplierId.getSelectionModel().select(rowData);
                    //cbSupplierId.setValue(rowData);
                }
            });
            return row;
        });

    }

    //load data in edit supplier combo box
    private void loadSuppliers() {
        ObservableList<Supplier> suppliers = SupplierDB.getSuppliers();
        cbSupplierId.setItems(suppliers);
    }


    //enables text fields
    private void enableTextFields() {
        tfSupName.setDisable(false);

        tfSupName.setEditable(true);

    }

    private void clearFields() {
        tfSupName.clear();

    }

    private void clearFieldsAdd() {
        tfSupNameAdd.clear();

    }
}




