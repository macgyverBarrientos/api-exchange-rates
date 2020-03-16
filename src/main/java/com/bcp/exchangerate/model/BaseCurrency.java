package com.bcp.exchangerate.model;

public class BaseCurrency {
    private String currency;
    public BaseCurrency(){}
    public BaseCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
