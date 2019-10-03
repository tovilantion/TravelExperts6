package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Package {
    private SimpleIntegerProperty PackageId;
    private SimpleStringProperty PkgName;
    private SimpleStringProperty PkgStartDate;
    private SimpleStringProperty PkgEndDate;
    private SimpleStringProperty PkgDesc;
    private SimpleDoubleProperty PkgBasePrice;
    private SimpleDoubleProperty PkgAgencyCommission;

    //Class constructor

    public Package(int PackageId, String PkgName, String PkgStartDate, String PkgEndDate, String PkgDesc,
                   Double PkgBasePrice, Double PkgAgencyCommission){

        this.PackageId = new SimpleIntegerProperty(PackageId);
        this.PkgName = new SimpleStringProperty(PkgName);
        this.PkgStartDate = new SimpleStringProperty(PkgStartDate);
        this.PkgEndDate = new SimpleStringProperty(PkgEndDate);
        this.PkgDesc = new SimpleStringProperty(PkgDesc);
        this.PkgBasePrice = new SimpleDoubleProperty(PkgBasePrice);
        this.PkgAgencyCommission = new SimpleDoubleProperty(PkgAgencyCommission);


    }

    //Set/get Methods

    public int getPackageId(){
        return PackageId.get();
    }

    public SimpleIntegerProperty getPackageIdProperty(){
        return this.PackageId;
    }

    public void setPackageId(int PackageId){
        this.PackageId.set(PackageId);
    }

    public String getPkgName(){
        return PkgName.get();
    }

    public SimpleStringProperty getPkgNameProperty(){
        return this.PkgName;
    }

    public void setPkgName(String PkgName){
        this.PkgName.set(PkgName);
    }

    public String getPkgStartDate(){
        return PkgStartDate.get();
    }

    public SimpleStringProperty getPkgStartDateProperty(){
        return this.PkgStartDate;
    }

    public void setPkgStartDate(String PkgStartDate){
        this.PkgStartDate.set(PkgStartDate);
    }

    public String getPkgEndDate(){
        return PkgEndDate.get();
    }

    public SimpleStringProperty getPkgEndDateProperty(){
        return this.PkgEndDate;
    }

    public void setPkgEndDate(String PkgEndDate){
        this.PkgEndDate.set(PkgEndDate);
    }


    public String getPkgDesc(){
        return PkgStartDate.get();
    }

    public SimpleStringProperty getPkgDescProperty(){
        return this.PkgDesc;
    }

    public void setPkgDesc(String PkgDesc){
        this.PkgDesc.set(PkgDesc);
    }

    public Double getPkgBasePrice(){
        return PkgBasePrice.get();
    }

    public SimpleDoubleProperty getPkgBasePriceProperty(){
        return this.PkgBasePrice;
    }

    public void setPkgBasePrice(Double PkgBasePrice){
        this.PkgBasePrice.set(PkgBasePrice);
    }

    public Double getPkgAgencyCommission(){
        return PkgAgencyCommission.get();
    }

    public SimpleDoubleProperty getPkgAgencyCommissionProperty(){
        return this.PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(Double PkgAgencyCommission){
        this.PkgAgencyCommission.set(PkgAgencyCommission);
    }
}
