package org.billingfx.models;

import javafx.beans.property.SimpleStringProperty;

public class ProductTableData {

    private SimpleStringProperty id;
    private float quantity;
    private float price;


    public ProductTableData(Product product) {
        this.id = product.getId();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }

    public ProductTableData(SimpleStringProperty id, float quantity, float price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
}
