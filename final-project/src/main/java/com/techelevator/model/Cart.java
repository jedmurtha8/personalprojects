package com.techelevator.model;

import java.util.List;

public class Cart {

    private List<CartItem> cartItems;
    private double productTotal;
    private double taxRate;
    private double afterTaxTotal;

    public Cart() { }

    public Cart(List<CartItem> cartItems, double productTotal, double taxRate, double afterTaxTotal) {
        this.cartItems = cartItems;
        this.productTotal = productTotal;
        this.taxRate = taxRate;
        this.afterTaxTotal = afterTaxTotal;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(double productTotal) {
        this.productTotal = productTotal;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getAfterTaxTotal(double v) {
        return afterTaxTotal;
    }

    public void setAfterTaxTotal(double afterTaxTotal) {
        this.afterTaxTotal = afterTaxTotal;
    }
}
