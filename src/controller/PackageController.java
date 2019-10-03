package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import data.PackageDB;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Package;

public class PackageController {

    @FXML
    TableView<Package> tvPackageList;
    @FXML
    TableColumn<Package, Integer> colPackageId;
    @FXML
    TableColumn<Package, String> colPkgName;
    @FXML
    TableColumn<Package, String> colPkgStartDate;
    @FXML
    TableColumn<Package, String> colPkgEndDate;
    @FXML
    TableColumn<Package, String> colPkgDesc;
    @FXML
    TableColumn<Package, Double> colPkgBasePrice;
    @FXML
    TableColumn<Package, Double> colPkgAgencyCommission;
    @FXML
    private Tab tabEditPackage;
    @FXML
    private TabPane tpPackage;

    @FXML
    void initialize() {
        ObservableList<Package> packages = PackageDB.getPackages();
        colPackageId.setCellValueFactory(new PropertyValueFactory<Package, Integer>("PackageId"));
        colPkgName.setCellValueFactory(new PropertyValueFactory<Package, String>("PkgName"));
        colPkgStartDate.setCellValueFactory(new PropertyValueFactory<Package, String>("PkgStartDate"));
        colPkgEndDate.setCellValueFactory(new PropertyValueFactory<Package, String>("PkgEndDate"));
        colPkgDesc.setCellValueFactory(new PropertyValueFactory<Package, String>("PkgDesc"));
        colPkgBasePrice.setCellValueFactory(new PropertyValueFactory<Package, Double>("PkgBasePrice"));
        colPkgAgencyCommission.setCellValueFactory(new PropertyValueFactory<Package, Double>("PkgAgencyCommission"));

        //Fill TableView
        tvPackageList.setItems(packages);

        tvPackageList.setRowFactory(tv -> {
            TableRow<Package> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Package rowData = row.getItem();

//                    System.out.println(rowData);
                    // action
                    tpPackage.getSelectionModel().select(tabEditPackage);

                    //tfCustomerFirstName.setText(rowData.getCustFirstName());
                }
            });
            return row;
        });

    }
}
