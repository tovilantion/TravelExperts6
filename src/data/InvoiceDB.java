package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Booking;
import model.BookingDetail;
import model.Customer;
import model.Invoice;

public class InvoiceDB {

    public static Invoice getInvoice(Customer customer) {
        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setBookings(getCustBookings(customer));
        invoice.setBookingDetails(getCustDetailBookings(invoice.getBookings()));
        return invoice;
    }

    private static ObservableList<Booking> getCustBookings(Customer customer) {
        ObservableList<Booking> allBookings = BookingDB.getBookings();
        ObservableList<Booking> custBookings = FXCollections.observableArrayList();
        for (Booking booking: allBookings) {
            if (booking.getCustomerId() == customer.getCustomerId()) {
                custBookings.add(booking);
            }
        }
        return custBookings;
    }

    private static ObservableList<ObservableList<BookingDetail>> getCustDetailBookings(ObservableList<Booking> custBookings) {
        ObservableList<ObservableList<BookingDetail>> bookingDetails =
                FXCollections.observableArrayList(FXCollections.observableArrayList());

        for (Booking booking: custBookings) {
            bookingDetails.add(BookingDetailDB.getBookingDetails(booking.getBookingId()));
        }
        return bookingDetails;
    }
}
