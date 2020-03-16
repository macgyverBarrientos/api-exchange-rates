package com.bcp.exchangerate.model;

import java.util.List;

public class SimulateExchangeRate {
    private BaseAmount baseAmount;
    private ExchangeRate exchangeRate;
    private String origin;
    private List<TargetCurrency> targetCurrencies;
    private List<TargetAmount> targetAmounts;

    public BaseAmount getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BaseAmount baseAmount) {
        this.baseAmount = baseAmount;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<TargetCurrency> getTargetCurrencies() {
        return targetCurrencies;
    }

    public void setTargetCurrencies(List<TargetCurrency> targetCurrencies) {
        this.targetCurrencies = targetCurrencies;
    }

    public List<TargetAmount> getTargetAmounts() {
        return targetAmounts;
    }

    public void setTargetAmounts(List<TargetAmount> targetAmounts) {
        this.targetAmounts = targetAmounts;
    }
}
