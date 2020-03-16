package com.bcp.exchangerate.controller.mapper.impl;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.business.dto.InputListExchangeRates;
import com.bcp.exchangerate.controller.mapper.IListExchangeRatesMapper;
import com.bcp.exchangerate.model.CreateExchangeRate;
import com.bcp.exchangerate.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ListExchangeRatesMapper implements IListExchangeRatesMapper {
    @Autowired
    private ExchangeRateMapper exchangeRateMapper;
    @Override
    public InputListExchangeRates mapIn(String currency, String origin, String action) {
        if (currency==null && origin==null && action==null){
            return null;
        }
        InputListExchangeRates input = new InputListExchangeRates();
        input.setCurrency(currency);
        input.setOrigin(origin);
        input.setAction(action);
        return input;
    }

    @Override
    public ExchangeRates mapOut(List<DTOIntCreateExchangeRate> dtoIntCreateExchangeRates) {
        if (CollectionUtils.isEmpty(dtoIntCreateExchangeRates)){
            return null;
        }
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setData(getCreateExchangeRateList(dtoIntCreateExchangeRates));
        return exchangeRates;
    }

    private List<CreateExchangeRate> getCreateExchangeRateList(final List<DTOIntCreateExchangeRate> dtoIntCreateExchangeRateList){
        return dtoIntCreateExchangeRateList.stream().filter(Objects::nonNull).map(dtoIntCreateExchangeRate -> {
            return exchangeRateMapper.mapperCreateExchangeRate(dtoIntCreateExchangeRate);
        }).collect(Collectors.toList());
    }
}
