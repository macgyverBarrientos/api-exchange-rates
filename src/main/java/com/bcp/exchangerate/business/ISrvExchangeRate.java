package com.bcp.exchangerate.business;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.business.dto.InputCreateExchangeRate;
import com.bcp.exchangerate.business.dto.InputListExchangeRates;
import com.bcp.exchangerate.business.dto.InputSimulateExchangeRate;
import com.bcp.exchangerate.model.SimulateExchangeRate;

import java.util.List;

public interface ISrvExchangeRate {
    DTOIntCreateExchangeRate createExchangeRate(InputCreateExchangeRate input);
    DTOIntCreateExchangeRate getTrade(Long idExchangeRate);
    //Trade modifyTrade(Trade trade);
    Boolean existExchangeRate(Long idExchangeRate);
    List<DTOIntCreateExchangeRate> listExchangeRates(InputListExchangeRates input);
    SimulateExchangeRate simulateExchangeRate(InputSimulateExchangeRate input);
}
