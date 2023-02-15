package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TaxRateResponse {
    @JsonProperty("salesTax")
    private double taxRate;
    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
    public TaxRateResponse(double taxRate) {this.taxRate = taxRate;}

    public TaxRateResponse() {

    }
}

