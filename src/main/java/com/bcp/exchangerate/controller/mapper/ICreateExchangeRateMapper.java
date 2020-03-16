package com.bcp.exchangerate.controller.mapper;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.business.dto.InputCreateExchangeRate;
import com.bcp.exchangerate.model.CreateExchangeRate;
import com.bcp.exchangerate.model.ExchangeRateResponse;

public interface ICreateExchangeRateMapper {
    InputCreateExchangeRate mapIn(CreateExchangeRate exchangeRate);
    ExchangeRateResponse mapOut(DTOIntCreateExchangeRate dtoIntCreateExchangeRate);
}
