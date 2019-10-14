package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class Booking {
    private SimpleIntegerProperty bookingId;
    private SimpleStringProperty bookingDate;
   // private SimpleObjectProperty<Date> bookingDate;
    private SimpleStringProperty bookingNo;
    private SimpleIntegerProperty travelerCount;
    private SimpleIntegerProperty customerId;
    private SimpleStringProperty tripTypeId;
    private SimpleIntegerProperty packageId;



    public Booking(int bookingId, String bookingDate, String bookingNo, int travelerCount, int customerId, String tripTypeId, int packageId) {
        this.bookingId = new SimpleIntegerProperty(bookingId);
        this.bookingDate = new SimpleStringProperty(bookingDate);
    //    this.bookingDate = new SimpleObjectProperty<Date>((Date) bookingDate);
        this.bookingNo = new SimpleStringProperty(bookingNo);
        this.travelerCount = new SimpleIntegerProperty(travelerCount);
        this.customerId =new SimpleIntegerProperty(customerId);
        this.tripTypeId = new SimpleStringProperty(tripTypeId);
        this.packageId = new SimpleIntegerProperty(packageId);
    }

        public Booking () {};

       public Booking(String bookingDate, String bookingNo, int travelerCount, int customerId, String tripTypeId, int packageId) {

           this.bookingDate = new SimpleStringProperty(bookingDate);
        //   this.bookingDate = new SimpleObjectProperty<Date>((Date) bookingDate);
           this.bookingNo = new SimpleStringProperty(bookingNo);
           this.travelerCount = new SimpleIntegerProperty(travelerCount);
           this.customerId = new SimpleIntegerProperty(customerId);
           this.tripTypeId = new SimpleStringProperty(tripTypeId);
           this.packageId = new SimpleIntegerProperty(packageId);
       }

    public Booking(SimpleStringProperty bookingDate, SimpleStringProperty bookingNo, SimpleIntegerProperty travelerCount, SimpleIntegerProperty customerId, SimpleStringProperty tripTypeId, SimpleIntegerProperty packageId) {

           this.bookingDate = bookingDate;
        this.bookingNo = bookingNo;
        this.travelerCount = travelerCount;
        this.customerId = customerId;
        this.tripTypeId = tripTypeId;
        this.packageId = packageId;
    }

   /* public Booking(int bookingId, Object bookingDate, String bookingNo, int travelerCount, int customerId, String tripTypeId, int packageId) {

    }*/


    public int getBookingId() {
        return bookingId.get();
    }

    public SimpleIntegerProperty bookingIdProperty() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId.set(bookingId);
    }

    public String getBookingDate() {
        return bookingDate.get();
    }

    public SimpleStringProperty bookingDateProperty() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate.set(String.valueOf(bookingDate));
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

    @Override
    public String toString() {
        return String.valueOf(getBookingId());
    }
}

