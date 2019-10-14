package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.BookingDetail;

import java.net.URL;
import java.util.ResourceBundle;

public class BookingDetailsController implements Initializable {
    @FXML
    private GridPane detailsGrid;
    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }

    void loadBookingDetails(ObservableList<BookingDetail> bookingDetails) {
        for (int i = 1; i <= bookingDetails.size(); i++) {
            BookingDetail bkDet = bookingDetails.get(i - 1);
            detailsGrid.add(new Label(bkDet.getProductSupplierId() + ""),0, i);
            detailsGrid.add(new Label(bkDet.getDescription()), 1, i);
            detailsGrid.add(new Label(bkDet.getDestination()), 2, i);
            detailsGrid.add(new Label(bkDet.getTripStart()), 3, i);
            detailsGrid.add(new Label(bkDet.getTripEnd()),4,i);
            detailsGrid.add(new Label(bkDet.getBasePrice() + ""), 5,i);

        }

        detailsGrid.setPadding(new Insets(20,10,20,10));



    }


    @FXML
    public GridPane getGridDetails() {
        return detailsGrid;
    }
}

