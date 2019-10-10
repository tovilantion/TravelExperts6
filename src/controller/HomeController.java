package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button btnDashboard;
    @FXML private Button btnCustomers;
    @FXML private Button btnBookings;
    @FXML private Button btnPackages;
    @FXML private Button btnSuppliers;
    @FXML private Button btnInvoices;
    @FXML private Button btnSignOut;
    @FXML private Pane pnlInvoices;
    @FXML private Pane pnlCustomer;
    @FXML private Pane pnlBookings;
    @FXML private Pane pnlPackages;
    @FXML private Pane pnlSuppliers;
    @FXML private Pane pnlDashboard;
    @FXML private VBox pnItems;
    @FXML private VBox vboxPnlCustomer;
    @FXML private Button btnClose;


    @FXML
    void onActionBtnClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML void onActionBtnBookings(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("../views/booking.fxml"));
        pnlBookings.getChildren().setAll(pane);
        pnlBookings.toFront();

    }

    @FXML void onActionBtnCustomers(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("../views/customer.fxml"));
        pnlCustomer.getChildren().setAll(pane);
        pnlCustomer.toFront();

    }

    @FXML void onActionBtnDashboard(ActionEvent event) throws IOException {
        pnlDashboard.toFront();
    }

    @FXML void onActionBtnInvoices(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("../views/invoice.fxml"));
        pnlInvoices.getChildren().setAll(pane);
        pnlInvoices.toFront();
    }

    @FXML void onActionBtnPackages(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("../views/package.fxml"));
        pnlPackages.getChildren().setAll(pane);
        pnlPackages.toFront();

    }

    @FXML void onActionBtnSuppliers(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("../views/supplier.fxml"));
        pnlSuppliers.getChildren().setAll(pane);
        pnlSuppliers.toFront();
    }


    @FXML void onActionBtnSignOut(ActionEvent event) {

    }
    @FXML void initialize() {

    }
}

