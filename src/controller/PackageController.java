package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import data.PackageDB;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Package;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private TextField tfPkgName;
    @FXML
    private DatePicker dpPkgStartDate;
    @FXML
    private DatePicker dpPkgEndDate;
    @FXML TextField tfPkgBasePrice;
    @FXML TextField tfPkgAgencyCommission;
    @FXML TextArea taPkgDesc;
    @FXML ComboBox cbPackageId;
    @FXML TextField tfAddPkgName;
    @FXML DatePicker dpAddPkgStartDate;
    @FXML DatePicker dpAddPkgEndDate;
    @FXML TextField tfAddPkgBasePrice;
    @FXML TextField tfAddPkgAgencyCommission;
    @FXML TextArea taAddPkgDesc;

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

                    // action
                    tpPackage.getSelectionModel().select(tabEditPackage);
                    cbPackageId.setValue(rowData);

                    //Set default text fields to existing data
                    tfPkgName.setText(rowData.getPkgName());
                    //Convert Date String to LocalDate
                    try {
                        Date dStart = new SimpleDateFormat("yyyy-MM-dd").parse(rowData.getPkgStartDate()); // This throws a ParseException
                        Date dEnd = new SimpleDateFormat("yyyy-MM-dd").parse(rowData.getPkgEndDate());

                        dpPkgStartDate.setValue(dStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        dpPkgEndDate.setValue(dEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    tfPkgAgencyCommission.setText(rowData.getPkgAgencyCommission().toString());
                    tfPkgBasePrice.setText(rowData.getPkgBasePrice().toString());
                    taPkgDesc.setText(rowData.getPkgDesc());

                }//end on click if statement
            });
            return row;
        });

        //Fill ComboBox
        cbPackageId.setItems(packages);

        //Set initial textfield status
        initializeFieldState(true);
    } //end initialize

    public void loadComboBox(){ //on click method for ComboBox
        Package selectedPackage = (Package)cbPackageId.getSelectionModel().getSelectedItem();
        cbPackageId.setValue(selectedPackage);
        tfPkgName.setText(selectedPackage.getPkgName());
        //Set dates
        try {
            Date dStart = new SimpleDateFormat("yyyy-MM-dd").parse(selectedPackage.getPkgStartDate()); // This throws a ParseException
            Date dEnd = new SimpleDateFormat("yyyy-MM-dd").parse(selectedPackage.getPkgEndDate());

            dpPkgStartDate.setValue(dStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            dpPkgEndDate.setValue(dEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tfPkgBasePrice.setText(selectedPackage.getPkgBasePrice().toString());
        tfPkgAgencyCommission.setText(selectedPackage.getPkgAgencyCommission().toString());
        taPkgDesc.setText(selectedPackage.getPkgDesc());

    }//end loadComboBox

    public void editPackage(){

        //Set initial textfield status
        initializeFieldState(false);

    }

    public void savePackage(){

        //Set fields to disabled again
        initializeFieldState(true);

        String pkgStartDateString = dpPkgStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String pkgEndDateString = dpPkgEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Package selectedPackage = new Package(Integer.valueOf(cbPackageId.getValue().toString()), tfPkgName.getText(), pkgStartDateString, pkgEndDateString,
                taPkgDesc.getText(), Double.valueOf(tfPkgBasePrice.getText()), Double.valueOf(tfPkgAgencyCommission.getText()));

        try {
            PackageDB.editPackage(selectedPackage);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ObservableList<Package> packages = PackageDB.getPackages();
        //Fill TableView
        tvPackageList.setItems(packages);
    }

    public void initializeFieldState(boolean status){
        //Set initial textfield status
        tfPkgName.setDisable(status);
        dpPkgStartDate.setDisable(status);
        dpPkgEndDate.setDisable(status);
        tfPkgBasePrice.setDisable(status);
        tfPkgAgencyCommission.setDisable(status);
        taPkgDesc.setDisable(status);

    }

    public void addPackage(){

        String pkgStartDateString = dpAddPkgStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String pkgEndDateString = dpAddPkgEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Package selectedPackage = new Package(0, tfAddPkgName.getText(), pkgStartDateString, pkgEndDateString,
                taAddPkgDesc.getText(), Double.valueOf(tfAddPkgBasePrice.getText()), Double.valueOf(tfAddPkgAgencyCommission.getText()));

        try {
            PackageDB.insertPackage(selectedPackage);
        }catch(Exception e){

        }

    }

    public void clearFields(){
        tfAddPkgName.clear();
        dpAddPkgStartDate.setValue(null);
        dpAddPkgEndDate.setValue(null);
        tfAddPkgBasePrice.clear();
        tfAddPkgAgencyCommission.clear();
        taAddPkgDesc.clear();

    }


}
