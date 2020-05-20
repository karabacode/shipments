package org.karabacode.shipmentapi.request;

import org.karabacode.shipmentapi.model.ProductOrder;

import java.util.List;

public class ShipmentCreateRequest {

    List<ProductOrder> orders;
    private String address;

    public List<ProductOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ProductOrder> orders) {
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
