package com.example.xenium.pocket.dto;

public class Cart {

    private String userId;
    private String productId;
    private String amount;

    public Cart(String userId, String productId, String amount) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
