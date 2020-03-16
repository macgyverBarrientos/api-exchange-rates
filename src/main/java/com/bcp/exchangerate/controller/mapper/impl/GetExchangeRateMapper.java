package com.bcp.exchangerate.controller.mapper.impl;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.controller.mapper.IGetExchangeRateMapper;
import com.bcp.exchangerate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetExchangeRateMapper implements IGetExchangeRateMapper {
    @Autowired
    private ExchangeRateMapper exchangeRateMapper;
    @Override
    public ExchangeRateResponse mapOut(final DTOIntCreateExchangeRate exchangeRate) {
        if (exchangeRate==null){
            return null;
        }
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setData(exchangeRateMapper.mapperCreateExchangeRate(exchangeRate));
        return response;
    }

}
