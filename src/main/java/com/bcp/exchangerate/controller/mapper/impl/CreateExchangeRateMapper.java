package com.bcp.exchangerate.controller.mapper.impl;

import com.bcp.exchangerate.business.dto.*;
import com.bcp.exchangerate.controller.mapper.ICreateExchangeRateMapper;
import com.bcp.exchangerate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreateExchangeRateMapper implements ICreateExchangeRateMapper {
    @Autowired
    private ExchangeRateMapper exchangeRateMapper;
    @Override
    public InputCreateExchangeRate mapIn(final CreateExchangeRate exchangeRate) {
        InputCreateExchangeRate input = new InputCreateExchangeRate();
        if (exchangeRate==null){
            return input;
        }
        input.setCreateExchangeRate(new DTOIntCreateExchangeRate());
        input.getCreateExchangeRate().setOrigin(exchangeRate.getOrigin());
        if (exchangeRate.getBaseCurrency()!=null){
            input.getCreateExchangeRate().setCurrency(exchangeRate.getBaseCurrency().getCurrency());
        }
        input.getCreateExchangeRate().setTargetCurrencies(getDTOIntTargetCurrencyList(exchangeRate.getTargetCurrencies()));
        return input;
    }

    @Override
    public ExchangeRateResponse mapOut(final DTOIntCreateExchangeRate exchangeRate) {
        if (exchangeRate==null){
            return null;
        }
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setData(exchangeRateMapper.mapperCreateExchangeRate(exchangeRate));
        return response;
    }

    private List<DTOIntTargetCurrency> getDTOIntTargetCurrencyList(final List<TargetCurrency> targetCurrencies){
        if (CollectionUtils.isEmpty(targetCurrencies)){
            return null;
        }
        return targetCurrencies.stream().filter(Objects::nonNull).map(this::getDTOIntTargetCurrency).collect(Collectors.toList());
    }
    private DTOIntTargetCurrency getDTOIntTargetCurrency(final TargetCurrency targetCurrency){
        DTOIntTargetCurrency dtoIntTargetCurrency = new DTOIntTargetCurrency();
        dtoIntTargetCurrency.setCurrency(targetCurrency.getCurrency());
        dtoIntTargetCurrency.setLastExchangeUpdate(targetCurrency.getLastExchangeUpdate());
        dtoIntTargetCurrency.setExchangeRateBuy(getDtoIntExchangeRateBuy(targetCurrency.getExchangeRates()));
        dtoIntTargetCurrency.setExchangeRateSell(getDtoIntExchangeRateSell(targetCurrency.getExchangeRates()));

        return dtoIntTargetCurrency;
    }
    private DTOIntExchangeRateBuy getDtoIntExchangeRateBuy(final List<ExchangeRate> exchangeRates){
        if (CollectionUtils.isEmpty(exchangeRates)){
            return null;
        }
        Optional<DTOIntExchangeRateBuy> exchangeRateBuy =exchangeRates.stream().filter(exchangeRate -> "FIXING_BUY".equalsIgnoreCase(exchangeRate.getAction())).map(exchangeRate -> {
            DTOIntExchangeRateBuy dtoIntExchangeRateBuy = new DTOIntExchangeRateBuy();
            dtoIntExchangeRateBuy.setAction(exchangeRate.getAction());
            dtoIntExchangeRateBuy.setValue(exchangeRate.getValue());
            return dtoIntExchangeRateBuy;
        }).findFirst();
        return exchangeRateBuy.orElse(null);
    }
    private DTOIntExchangeRateSell getDtoIntExchangeRateSell(final List<ExchangeRate> exchangeRates){
        if (CollectionUtils.isEmpty(exchangeRates)){
            return null;
        }
        Optional<DTOIntExchangeRateSell> exchangeRateSell = exchangeRates.stream().filter(exchangeRate -> "FIXING_SELL".equalsIgnoreCase(exchangeRate.getAction())).map(exchangeRate -> {
            DTOIntExchangeRateSell dtoIntExchangeRateSell = new DTOIntExchangeRateSell();
            dtoIntExchangeRateSell.setAction(exchangeRate.getAction());
            dtoIntExchangeRateSell.setValue(exchangeRate.getValue());
            return dtoIntExchangeRateSell;
        }).findFirst();
        return exchangeRateSell.orElse(null);
    }
}
