package com.bcp.exchangerate.controller.mapper.impl;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.business.dto.DTOIntTargetCurrency;
import com.bcp.exchangerate.model.BaseCurrency;
import com.bcp.exchangerate.model.CreateExchangeRate;
import com.bcp.exchangerate.model.ExchangeRate;
import com.bcp.exchangerate.model.TargetCurrency;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ExchangeRateMapper {
    public CreateExchangeRate mapperCreateExchangeRate(final DTOIntCreateExchangeRate exchangeRate){
        CreateExchangeRate createExchangeRate = new CreateExchangeRate();
        createExchangeRate.setId(exchangeRate.getId());
        createExchangeRate.setOrigin(exchangeRate.getOrigin());
        if (exchangeRate.getCurrency()!=null){
            createExchangeRate.setBaseCurrency(new BaseCurrency());
            createExchangeRate.getBaseCurrency().setCurrency(exchangeRate.getCurrency());
        }
        createExchangeRate.setTargetCurrencies(targetCurrencyList(exchangeRate.getTargetCurrencies()));
        return createExchangeRate;
    }

    private List<TargetCurrency> targetCurrencyList(final List<DTOIntTargetCurrency> dtoIntTargetCurrencies){
        if (CollectionUtils.isEmpty(dtoIntTargetCurrencies)){
            return null;
        }
        return dtoIntTargetCurrencies.stream().filter(Objects::nonNull).map(this::getTargetCurrency).collect(Collectors.toList());
    }

    private TargetCurrency getTargetCurrency(final DTOIntTargetCurrency dtoIntTargetCurrency){
        TargetCurrency targetCurrency = new TargetCurrency();
        targetCurrency.setCurrency(dtoIntTargetCurrency.getCurrency());
        targetCurrency.setLastExchangeUpdate(dtoIntTargetCurrency.getLastExchangeUpdate());
        targetCurrency.setId(dtoIntTargetCurrency.getId());
        if (dtoIntTargetCurrency.getExchangeRateBuy()!=null || dtoIntTargetCurrency.getExchangeRateSell()!=null){
            targetCurrency.setExchangeRates(new ArrayList<>());
            if (dtoIntTargetCurrency.getExchangeRateBuy()!=null){
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setAction(dtoIntTargetCurrency.getExchangeRateBuy().getAction());
                exchangeRate.setValue(dtoIntTargetCurrency.getExchangeRateBuy().getValue());
                targetCurrency.getExchangeRates().add(exchangeRate);
            }
            if (dtoIntTargetCurrency.getExchangeRateSell()!=null){
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setAction(dtoIntTargetCurrency.getExchangeRateSell().getAction());
                exchangeRate.setValue(dtoIntTargetCurrency.getExchangeRateSell().getValue());
                targetCurrency.getExchangeRates().add(exchangeRate);
            }
        }
        return targetCurrency;
    }
}

