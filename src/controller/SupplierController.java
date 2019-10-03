package controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import data.SupplierDB;
import data.CustomerDB;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supplier;


public class SupplierController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableColumn<Supplier, Integer> colSupplierId;
    @FXML private TableColumn<Supplier, String> colSupName;

    @FXML private TableView<Supplier> tvSupplierList;
    @FXML private Tab tabEditSupplier;
    @FXML private TabPane tpSuppliers;


    @FXML void initialize() {



        ObservableList<Supplier> suppliers = SupplierDB.getSuppliers();
        colSupplierId.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty().asObject());
        colSupName.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

        tvSupplierList.setItems(suppliers);




        tvSupplierList.setRowFactory(tv -> {
            TableRow<Supplier> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Supplier rowData = row.getItem();
                    //  System.out.println(rowData);  //this row was commented out
                    // action
                    tpSuppliers.getSelectionModel().select(tabEditSupplier);
                }
            });
            return row;
        });


//         lvCustomer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


}

