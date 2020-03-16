package com.bcp.exchangerate.business.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Entity
@Table(name = "target_currency")
public class DTOIntTargetCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String currency;
    private Timestamp lastExchangeUpdate;
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "action", column = @Column(name = "action_buy")),
            @AttributeOverride(name = "value", column = @Column(name = "value_buy"))
    })
    private DTOIntExchangeRateBuy exchangeRateBuy;
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "action", column = @Column(name = "action_sell")),
            @AttributeOverride(name = "value", column = @Column(name = "value_sell"))
    })
    private DTOIntExchangeRateSell exchangeRateSell;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exchange_rate_id", nullable = true)
    private DTOIntCreateExchangeRate exchangeRate;
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

    public DTOIntExchangeRateBuy getExchangeRateBuy() {
        return exchangeRateBuy;
    }

    public void setExchangeRateBuy(DTOIntExchangeRateBuy exchangeRateBuy) {
        this.exchangeRateBuy = exchangeRateBuy;
    }

    public DTOIntExchangeRateSell getExchangeRateSell() {
        return exchangeRateSell;
    }

    public void setExchangeRateSell(DTOIntExchangeRateSell exchangeRateSell) {
        this.exchangeRateSell = exchangeRateSell;
    }

    public DTOIntCreateExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(DTOIntCreateExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
