package com.bcp.exchangerate.controller.mapper;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.business.dto.InputListExchangeRates;
import com.bcp.exchangerate.model.ExchangeRates;

import java.util.List;

public interface IListExchangeRatesMapper {
    InputListExchangeRates mapIn(String currency, String origin, String action);
    ExchangeRates mapOut(List<DTOIntCreateExchangeRate> dtoIntCreateExchangeRates);
}
