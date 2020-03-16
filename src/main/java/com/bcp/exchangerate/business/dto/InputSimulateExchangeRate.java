package com.bcp.exchangerate.business.dto;

import javax.validation.constraints.NotNull;

public class InputSimulateExchangeRate {
    @NotNull
    private DTOIntSimulateExchangeRate simulateExchangeRate;

    public DTOIntSimulateExchangeRate getSimulateExchangeRate() {
        return simulateExchangeRate;
    }

    public void setSimulateExchangeRate(DTOIntSimulateExchangeRate simulateExchangeRate) {
        this.simulateExchangeRate = simulateExchangeRate;
    }
}
