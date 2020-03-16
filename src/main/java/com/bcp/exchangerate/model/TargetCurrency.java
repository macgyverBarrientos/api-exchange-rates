package com.bcp.exchangerate.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
public class TargetCurrency {
    private Long id;
    private String currency;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp lastExchangeUpdate;
    private List<ExchangeRate> exchangeRates;
    private BigDecimal netAmount;
    private BigDecimal feeAmount;
    private BigDecimal totalAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Timestamp getLastExchangeUpdate() {
        return lastExchangeUpdate;
    }

    public void setLastExchangeUpdate(Timestamp lastExchangeUpdate) {
        this.lastExchangeUpdate = lastExchangeUpdate;
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
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
}
