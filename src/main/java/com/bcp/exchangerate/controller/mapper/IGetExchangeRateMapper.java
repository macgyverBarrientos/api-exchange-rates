package com.bcp.exchangerate.controller.mapper;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.model.ExchangeRateResponse;

public interface IGetExchangeRateMapper {
    ExchangeRateResponse mapOut(DTOIntCreateExchangeRate exchangeRate);
}
