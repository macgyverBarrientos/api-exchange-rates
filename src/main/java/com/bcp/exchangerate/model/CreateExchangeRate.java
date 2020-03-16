package com.bcp.exchangerate.model;

import java.util.List;

public class CreateExchangeRate {
    private Long id;
    private BaseCurrency baseCurrency;
    private List<TargetCurrency> targetCurrencies;
    private String origin;
    public CreateExchangeRate(){}

    public CreateExchangeRate( String origin) {
        this.origin = origin;
    }

    public BaseCurrency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(BaseCurrency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TargetCurrency> getTargetCurrencies() {
        return targetCurrencies;
    }

    public void setTargetCurrencies(List<TargetCurrency> targetCurrencies) {
        this.targetCurrencies = targetCurrencies;
    }
}
