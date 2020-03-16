package com.bcp.exchangerate.model;


import java.math.BigDecimal;
public class ExchangeRate {
    private String action;
    private BigDecimal value;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
