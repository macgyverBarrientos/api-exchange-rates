package com.bcp.exchangerate.model;

import java.util.List;

public class ExchangeRates {
    private List<CreateExchangeRate> data;

    public List<CreateExchangeRate> getData() {
        return data;
    }

    public void setData(List<CreateExchangeRate> data) {
        this.data = data;
    }
}
