package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class Booking {
    private SimpleIntegerProperty bookingId;
    private SimpleObjectProperty<Date> bookingDate;
    private SimpleStringProperty bookingNo;
    private SimpleIntegerProperty travelerCount;
    private SimpleIntegerProperty customerId;
    private SimpleStringProperty tripTypeId;
    private SimpleIntegerProperty packageId;



    public Booking() {
        this.bookingId = new SimpleIntegerProperty();
        this.bookingDate = new SimpleObjectProperty<>();
        this.bookingNo = new SimpleStringProperty();
        this.travelerCount = new SimpleIntegerProperty();
        this.customerId =new SimpleIntegerProperty();
        this.tripTypeId = new SimpleStringProperty();
        this.packageId = new SimpleIntegerProperty();
    }



    public int getBookingId() {
        return bookingId.get();
    }

    public SimpleIntegerProperty bookingIdProperty() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId.set(bookingId);
    }

    public Object getBookingDate() {
        return bookingDate.get();
    }

    public SimpleObjectProperty<Date> bookingDateProperty() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate.set(bookingDate);
    }


    public String getBookingNo() {
        return bookingNo.get();
    }

    public SimpleStringProperty bookingNoProperty() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo.set(bookingNo);
    }

    public int getTravelerCount() {
        return travelerCount.get();
    }

    public SimpleIntegerProperty travelerCountProperty() {
        return travelerCount;
    }

    public void setTravelerCount(int travelerCount) {
        this.travelerCount.set(travelerCount);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public String getTripTypeId() {
        return tripTypeId.get();
    }

    public SimpleStringProperty tripTypeIdProperty() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId.set(tripTypeId);
    }

    public int getPackageId() {
        return packageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId.set(packageId);
    }
}
