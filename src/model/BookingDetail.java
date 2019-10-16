package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class BookingDetail {

    private SimpleIntegerProperty bookingDetailId;
    private SimpleIntegerProperty itineraryNo;
    private SimpleStringProperty tripStart;
    private SimpleStringProperty tripEnd;
    private SimpleStringProperty destination;
    private SimpleStringProperty description;
    private SimpleDoubleProperty basePrice;
    private SimpleDoubleProperty agencyCommission;
    private SimpleIntegerProperty bookingId;
    private SimpleStringProperty regionId;
    private SimpleStringProperty classId;
    private SimpleStringProperty feeId;
    private SimpleIntegerProperty productSupplierId;

    public BookingDetail(int bookingDetailId,
                         int itineraryNo,
                         String tripStart,
                         String tripEnd,
                         String description,
                         String destination,
                         double basePrice,
                         double agencyCommission,
                         int bookingId,
                         String regionId,
                         String classId,
                         String feeId,
                         int productSupplierId) {
        this.bookingDetailId = new SimpleIntegerProperty(bookingDetailId);
        this.itineraryNo = new SimpleIntegerProperty(itineraryNo);
        this.tripStart = new SimpleStringProperty(tripStart);
        this.tripEnd = new SimpleStringProperty(tripEnd);
        this.description = new SimpleStringProperty(description);
        this.destination = new SimpleStringProperty(destination);
        this.basePrice = new SimpleDoubleProperty(basePrice);
        this.agencyCommission = new SimpleDoubleProperty(agencyCommission);
        this.bookingId = new SimpleIntegerProperty(bookingId);
        this.regionId = new SimpleStringProperty(regionId);
        this.classId = new SimpleStringProperty(classId);
        this.feeId = new SimpleStringProperty(feeId);
        this.productSupplierId = new SimpleIntegerProperty(productSupplierId);
    }

    public BookingDetail(int bookingDetailId, int itineraryNo, String tripStart, String tripEnd, String destination, String description, double basePrice) {
        this.bookingDetailId = new SimpleIntegerProperty(bookingDetailId);
        this.itineraryNo =new SimpleIntegerProperty(itineraryNo);
        this.tripStart = new SimpleStringProperty(tripStart);
        this.tripEnd = new SimpleStringProperty(tripEnd);
        this.destination = new SimpleStringProperty(destination);
        this.description = new SimpleStringProperty(description);
        this.basePrice = new SimpleDoubleProperty(basePrice);
    }

    public BookingDetail() {
    }

    public int getItineraryNo() {
        return itineraryNo.get();
    }

    public SimpleIntegerProperty itineraryNoProperty() {
        return itineraryNo;
    }

    public void setItineraryNo(int itineraryNo) {
        this.itineraryNo.set(itineraryNo);
    }

    public int getBookingDetailId() {
        return bookingDetailId.get();
    }

    public SimpleIntegerProperty bookingDetailIdProperty() {
        return bookingDetailId;
    }

    public void setBookingDetailId(int bookingDetailId) {
        this.bookingDetailId.set(bookingDetailId);
    }

    public String getTripStart() {
        return tripStart.get();
    }

    public SimpleStringProperty tripStartProperty() {
        return tripStart;
    }

    public void setTripStart(String tripStart) {
        this.tripStart.set(tripStart);
    }

    public String getTripEnd() {
        return tripEnd.get();
    }

    public SimpleStringProperty tripEndProperty() {
        return tripEnd;
    }

    public void setTripEnd(String tripEnd) {
        this.tripEnd.set(tripEnd);
    }

    public String getDestination() {
        return destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getBasePrice() {
        return basePrice.get();
    }

    public SimpleDoubleProperty basePriceProperty() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice.set(basePrice);
    }

    public double getAgencyCommission() {
        return agencyCommission.get();
    }

    public SimpleDoubleProperty agencyCommissionProperty() {
        return agencyCommission;
    }

    public void setAgencyCommission(double agencyCommission) {
        this.agencyCommission.set(agencyCommission);
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

    public String getRegionId() {
        return regionId.get();
    }

    public SimpleStringProperty regionIdProperty() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId.set(regionId);
    }

    public String getClassId() {
        return classId.get();
    }

    public SimpleStringProperty classIdProperty() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId.set(classId);
    }

    public String getFeeId() {
        return feeId.get();
    }

    public SimpleStringProperty feeIdProperty() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId.set(feeId);
    }

    public int getProductSupplierId() {
        return productSupplierId.get();
    }

    public SimpleIntegerProperty productSupplierIdProperty() {
        return productSupplierId;
    }

    public void setProductSupplierId(int productSupplierId) {
        this.productSupplierId.set(productSupplierId);
    }


}
