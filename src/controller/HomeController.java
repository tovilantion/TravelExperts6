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

    @FXML void onActionBtnBookings(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("../views/booking.fxml"));
        pnlBookings.getChildren().setAll(pane);
        pnlBookings.toFront();
    }

    @FXML void onActionBtnCustomers(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("../views/customer.fxml"));
        pnlCustomer.getChildren().setAll(pane);
        pnlCustomer.toFront();
        //pnlCustomer.setVisible(true);

    }

    @FXML void onActionBtnDashboard(ActionEvent event) throws IOException {
        pnlDashboard.toFront();
        //pnlCustomer.setVisible(false);
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


//        Node[] nodes = new Node[10];
//        for (int i = 0; i < nodes.length; i++) {
//            try {
//
//                final int j = i;
//                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
//
//                //give the items some effect
//
//                nodes[i].setOnMouseEntered(event -> {
//                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
//                });
//                nodes[i].setOnMouseExited(event -> {
//                    nodes[j].setStyle("-fx-background-color : #02030A");
//                });
//                pnItems.getChildren().add(nodes[i]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//    }
//
//
//    public void handleClicks(ActionEvent actionEvent) throws IOException {
//        if (actionEvent.getSource() == btnCustomers) {
//            pnlCustomer.setStyle("-fx-background-color : #1620A1");
//            pnlCustomer.toFront();
//        }
//        if (actionEvent.getSource() == btnMenus) {
//            pnlMenus.setStyle("-fx-background-color : #53639F");
//            pnlMenus.toFront();
//        }
//        if (actionEvent.getSource() == btnOverview) {
//            pnlOverview.setStyle("-fx-background-color : white");
//            pnlOverview.toFront();
//        }
//        if(actionEvent.getSource()==btnOrders)
//        {
//            Pane pane = FXMLLoader.load(getClass().getResource("customer.fxml"));
//            pnlOverview.getChildren().setAll(pane);
//            //pnlOrders.setStyle("-fx-background-color : #464F67");
//            //pnlOrders.toFront();
//        }
//    }
//}
