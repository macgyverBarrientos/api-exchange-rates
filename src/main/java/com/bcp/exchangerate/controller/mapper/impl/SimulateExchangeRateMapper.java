package com.bcp.exchangerate.controller.mapper.impl;

import com.bcp.exchangerate.business.dto.*;
import com.bcp.exchangerate.controller.mapper.ISimulateExchangeRateMapper;
import com.bcp.exchangerate.model.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class SimulateExchangeRateMapper implements ISimulateExchangeRateMapper {
    @Override
    public InputSimulateExchangeRate mapIn(SimulateExchangeRate simulateExchangeRate) {
        DTOIntSimulateExchangeRate dtoIntSimulateExchangeRate = new DTOIntSimulateExchangeRate();
        dtoIntSimulateExchangeRate.setBaseAmount(getDtoIntBaseAmount(simulateExchangeRate.getBaseAmount()));
        dtoIntSimulateExchangeRate.setExchangeRate(getDtoIntExchangeRate(simulateExchangeRate.getExchangeRate()));
        dtoIntSimulateExchangeRate.setTargetCurrencies(getDTOIntTargetCurrencyList(simulateExchangeRate.getTargetCurrencies()));
        dtoIntSimulateExchangeRate.setOrigin(simulateExchangeRate.getOrigin());
        InputSimulateExchangeRate input = new InputSimulateExchangeRate();
        input.setSimulateExchangeRate(dtoIntSimulateExchangeRate);
        return input;
    }

    @Override
    public SimulateExchangeRateResponse mapOut(SimulateExchangeRate simulateExchangeRate) {
        if (simulateExchangeRate==null){
            return null;
        }
        SimulateExchangeRateResponse rateResponse = new SimulateExchangeRateResponse();
        rateResponse.setData(simulateExchangeRate);
        return rateResponse;
    }

    private DTOIntBaseAmount getDtoIntBaseAmount(final BaseAmount baseAmount){
        if (baseAmount == null){
            return null;
        }
        DTOIntBaseAmount dtoIntBaseAmount = new DTOIntBaseAmount();
        dtoIntBaseAmount.setAmount(baseAmount.getAmount());
        dtoIntBaseAmount.setCurrency(baseAmount.getCurrency());
        return dtoIntBaseAmount;
    }

    private DTOIntExchangeRate getDtoIntExchangeRate(final ExchangeRate exchangeRate){
        if (exchangeRate==null){
            return null;
        }
        DTOIntExchangeRate dtoIntExchangeRate = new DTOIntExchangeRate();
        dtoIntExchangeRate.setAction(exchangeRate.getAction());
        return dtoIntExchangeRate;
    }

    private String getDTOIntTargetCurrencyList(final List<TargetCurrency> targetCurrencies){
        if (CollectionUtils.isEmpty(targetCurrencies)){
            return null;
        }
        String targetDivisa = "";
        for (TargetCurrency targetCurrency: targetCurrencies){
            if (targetCurrency.getCurrency()!=null){
                targetDivisa = targetDivisa +"|"+targetCurrency.getCurrency();
            }
        }
       return targetDivisa;
    }

}
