package com.bcp.exchangerate.business.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DTOIntBaseAmount {
    @NotNull
    private String currency;
    @NotNull
    private BigDecimal amount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
