package model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class Invoice {
    private SimpleObjectProperty<Customer> customer;
    private SimpleObjectProperty<Agent> agent;
    private ObservableList<Booking> bookings;
    private ObservableList<ObservableList<BookingDetail>> bookingDetails;

    public Invoice() {}

    public Customer getCustomer() {
        return customer.get();
    }

    public SimpleObjectProperty<Customer> customerProperty() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = new SimpleObjectProperty<>(customer);
    }

    public Agent getAgent() {
        return agent.get();
    }

    public SimpleObjectProperty<Agent> agentProperty() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = new SimpleObjectProperty<>(agent);
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ObservableList<Booking> bookings) {
        this.bookings = bookings;
    }

    public ObservableList<ObservableList<BookingDetail>> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(ObservableList<ObservableList<BookingDetail>> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
