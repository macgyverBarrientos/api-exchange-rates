package com.bcp.exchangerate.business.dto;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
@Embeddable
public class DTOIntExchangeRateBuy {
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
