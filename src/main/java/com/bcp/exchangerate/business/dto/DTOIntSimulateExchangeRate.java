package com.bcp.exchangerate.business.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class DTOIntSimulateExchangeRate {
    @NotNull
    private DTOIntBaseAmount baseAmount;
    @NotNull
    private DTOIntExchangeRate exchangeRate;
    @NotNull
    private String origin;
    @NotNull
    private String targetCurrencies;

    public DTOIntBaseAmount getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(DTOIntBaseAmount baseAmount) {
        this.baseAmount = baseAmount;
    }

    public DTOIntExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(DTOIntExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTargetCurrencies() {
        return targetCurrencies;
    }

    public void setTargetCurrencies(String targetCurrencies) {
        this.targetCurrencies = targetCurrencies;
    }
}
