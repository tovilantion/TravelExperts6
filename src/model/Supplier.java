package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Supplier {

    private SimpleIntegerProperty supplierId;
    private SimpleStringProperty supName;

    public Supplier(int supplierId, String supName) {
        this.supplierId = new SimpleIntegerProperty(supplierId);
        this.supName = new SimpleStringProperty(supName);
    }

    public Supplier(String supName) {
        this.supName = new SimpleStringProperty(supName);
    }

    public Supplier(){};


    public int getSupplierId() {
        return supplierId.get();
    }

    public SimpleIntegerProperty supplierIdProperty() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId.set(supplierId);
    }

    public String getSupName() {
        return supName.get();
    }

    public SimpleStringProperty supNameProperty() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName.set(supName);
    }

/*    @Override
    public String toString() {
        return  supplierId + "";
    }
}*/

    @Override
    public String toString() {
        return String.valueOf(getSupplierId());
    }
}
