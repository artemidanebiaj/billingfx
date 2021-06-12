package org.billingfx.models;

public class ProductTableData {

    private String id;
    private Double quantity;
    private Double price;


    public ProductTableData(Product product) {
        this.id = product.getId();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }

    public ProductTableData(String id, Double quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
