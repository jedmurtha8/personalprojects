package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartItem {

    private int cartItemId;
    private int userId;
    private int productId;
    private int quantity;

    public CartItem(int id, int userId, int productId, int quantity) {
        this.cartItemId = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem() { }


    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
