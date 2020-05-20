package org.karabacode.shipmentapi.model;

import java.io.Serializable;
import java.util.List;

public class ShipmentModel {
    private String address;
    private List<ProductOrder> products;
    private String shipmentState;

    public String getShipmentState() {
        return shipmentState;
    }

    public void setShipmentState(String shipmentState) {
        this.shipmentState = shipmentState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrder> products) {
        this.products = products;
    }
}
