package com.bcp.exchangerate.business.dto;

import javax.validation.constraints.NotNull;

public class InputCreateExchangeRate {
    @NotNull
    private DTOIntCreateExchangeRate createExchangeRate;

    public DTOIntCreateExchangeRate getCreateExchangeRate() {
        return createExchangeRate;
    }

    public void setCreateExchangeRate(DTOIntCreateExchangeRate createExchangeRate) {
        this.createExchangeRate = createExchangeRate;
    }
}
