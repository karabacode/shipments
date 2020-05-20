package org.karabacode.shipmentapi.responses;

public class ShipmentCreateResponse {

    long shipmentId;
    String shipmentSate;
    String productName;

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentSate() {
        return shipmentSate;
    }

    public void setShipmentSate(String shipmentSate) {
        this.shipmentSate = shipmentSate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
