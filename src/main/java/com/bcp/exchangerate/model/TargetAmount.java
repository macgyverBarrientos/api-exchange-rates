package com.bcp.exchangerate.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TargetAmount {
    private ExchangeRate exchangeRate;
    private String currency;
    private BigDecimal netAmount;
    private BigDecimal feeAmount;
    private BigDecimal totalAmount;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp lastExchangeUpdate;

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getLastExchangeUpdate() {
        return lastExchangeUpdate;
    }

    public void setLastExchangeUpdate(Timestamp lastExchangeUpdate) {
        this.lastExchangeUpdate = lastExchangeUpdate;
    }
}
