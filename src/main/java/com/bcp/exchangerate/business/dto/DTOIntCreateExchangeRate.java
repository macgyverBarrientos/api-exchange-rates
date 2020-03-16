package com.bcp.exchangerate.business.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exchange_rate")
public class DTOIntCreateExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String currency;
    private String origin;
    @OneToMany(mappedBy = "exchangeRate", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<DTOIntTargetCurrency> targetCurrencies;

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<DTOIntTargetCurrency> getTargetCurrencies() {
        return targetCurrencies;
    }

    public void setTargetCurrencies(List<DTOIntTargetCurrency> targetCurrencies) {
        this.targetCurrencies = targetCurrencies;
    }
}
