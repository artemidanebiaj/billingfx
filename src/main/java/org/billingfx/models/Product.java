package org.billingfx.models;

public class Product {

    private String id;
    private Double quantity;
    private Double price;

    public Product(String id, Double quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double quantityProperty() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double priceProperty() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
