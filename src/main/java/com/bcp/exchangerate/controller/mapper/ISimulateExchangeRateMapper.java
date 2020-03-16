package com.bcp.exchangerate.controller.mapper;

import com.bcp.exchangerate.business.dto.InputSimulateExchangeRate;
import com.bcp.exchangerate.model.SimulateExchangeRate;
import com.bcp.exchangerate.model.SimulateExchangeRateResponse;

public interface ISimulateExchangeRateMapper {
    InputSimulateExchangeRate mapIn(SimulateExchangeRate simulateExchangeRate);
    SimulateExchangeRateResponse mapOut(SimulateExchangeRate simulateExchangeRate);
}
