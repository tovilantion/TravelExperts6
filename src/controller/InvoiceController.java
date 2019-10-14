package controller;

import data.CustomerDB;
import data.InvoiceDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Booking;
import model.BookingDetail;
import model.Customer;
import model.Invoice;

import java.io.IOException;

public class InvoiceController {

    @FXML
    private ComboBox<Customer> cbCustomers;
    @FXML
    private VBox customerCard;

    @FXML
    private VBox AgentCard;
    @FXML
    private Label lblCustPostalCode;
    @FXML
    private Label lblCustFullName;
    @FXML
    private Label lblCustAddress;
    @FXML
    private Label lblCustCity;
    @FXML
    private Label lblCustCountry;
    @FXML
    private VBox bookingsBox;
    @FXML
    private ScrollPane bookingsScroll;


    private void loadCustomers() {
        ObservableList<Customer> customers = CustomerDB.getCustomers();
        cbCustomers.setItems(customers);
    }

    @FXML
    void initialize() {
        loadCustomers();
    }

    @FXML
    void onActionCbCustomers(ActionEvent event) throws IOException {
        Customer selectedCustomer = cbCustomers.getSelectionModel().getSelectedItem();
        fillCustomerCard(selectedCustomer);
        Invoice invoice = InvoiceDB.getInvoice(selectedCustomer);
        fillCustomerBookings(invoice);
    }

    private void fillCustomerCard(Customer customer) {
        lblCustFullName.setText(customer.getCustFirstName() + " " + customer.getCustLastName());
        lblCustAddress.setText(customer.getCustAddress());
        lblCustCity.setText(customer.getCustCity() + ", " + customer.getCustProv());
        lblCustCountry.setText(customer.getCustCountry());
        lblCustPostalCode.setText(customer.getCustPostal());
    }

    private void fillCustomerBookings(Invoice invoice) throws IOException {
        bookingsBox.getChildren().clear();
        for (int i = 0; i < invoice.getBookings().size(); i++) {
            Booking booking = invoice.getBookings().get(i);
            ObservableList<BookingDetail> bookingDetails = invoice.getBookingDetails().get(i);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/bookingDisplay.fxml"));
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("../views/bookingDetails.fxml"));
            Pane pane = fxmlLoader.load();
            Pane pane1 = fxmlLoader1.load();
            BookingDisplayController controller = fxmlLoader.getController();
            BookingDetailsController controller1 = fxmlLoader1.getController();
            controller.bookingsDisplay(booking);
            controller1.loadBookingDetails(bookingDetails);
            bookingsBox.getChildren().addAll(pane, pane1);

        }


    }


}
