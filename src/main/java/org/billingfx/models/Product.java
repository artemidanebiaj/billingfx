package org.billingfx.models;

import javafx.beans.property.SimpleStringProperty;

public class Product {

    private SimpleStringProperty id;
    private float quantity;
    private float price;

    public Product(SimpleStringProperty id, float quantity, float price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public SimpleStringProperty getId() {
        return id;
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public float getQuantity() {
        return quantity;
    }

    public float quantityProperty() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public float priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
