package org.karabacode.shipmentapi.model;

import java.io.Serializable;

public class ProductOrder {

    private long productId;

    private int productQty;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }
}
