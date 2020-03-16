package com.bcp.exchangerate.controller;

import com.bcp.exchangerate.business.ISrvExchangeRate;
import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.business.dto.InputCreateExchangeRate;
import com.bcp.exchangerate.business.dto.InputListExchangeRates;
import com.bcp.exchangerate.business.dto.InputSimulateExchangeRate;
import com.bcp.exchangerate.controller.mapper.ICreateExchangeRateMapper;
import com.bcp.exchangerate.controller.mapper.IGetExchangeRateMapper;
import com.bcp.exchangerate.controller.mapper.IListExchangeRatesMapper;
import com.bcp.exchangerate.controller.mapper.ISimulateExchangeRateMapper;
import com.bcp.exchangerate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exchange-rates/v0")
public class SrvExchangeRatesV0 {
    @Autowired
    private ISrvExchangeRate srvExchangeRate;
    @Autowired
    private ICreateExchangeRateMapper createExchangeRateMapper;
    @Autowired
    private IGetExchangeRateMapper getExchangeRateMapper;
    @Autowired
    private IListExchangeRatesMapper listExchangeRatesMapper;
    @Autowired
    private ISimulateExchangeRateMapper simulateExchangeRateMapper;
    @PostMapping(path = "/exchange-rates")
    public ResponseEntity createExchangeRate(@RequestBody CreateExchangeRate exchangeRate){
        InputCreateExchangeRate input = createExchangeRateMapper.mapIn(exchangeRate);
        DTOIntCreateExchangeRate output = srvExchangeRate.createExchangeRate(input);
        return ResponseEntity.status(201).body(createExchangeRateMapper.mapOut(output));
    }

    @GetMapping("/exchange-rates/{exchange-rate-id}")
    @ResponseStatus(HttpStatus.OK)
    public ExchangeRateResponse getExchangeRate(@PathVariable(name = "exchange-rate-id") final Long idExchangeRate) {
        DTOIntCreateExchangeRate dtoIntCreateExchangeRate = srvExchangeRate.getTrade(idExchangeRate);
        return getExchangeRateMapper.mapOut(dtoIntCreateExchangeRate);
    }

    @GetMapping("/exchange-rates")
    @ResponseStatus(HttpStatus.OK)
    public ExchangeRates listExchangeRates(@RequestParam(value = "baseCurrency.currency",required = false) final String baseCurrencyId,
                                           @RequestParam(value ="targetCurrency.exchangeRate.action", required = false) final String action,
                                           @RequestParam(value = "origin", required = false) final String origin){
        InputListExchangeRates input = listExchangeRatesMapper.mapIn(baseCurrencyId,origin,action);
        List<DTOIntCreateExchangeRate> response = srvExchangeRate.listExchangeRates(input);
        return listExchangeRatesMapper.mapOut(response);
    }
    @PostMapping(path = "/simulate")
    @ResponseStatus(HttpStatus.OK)
    public SimulateExchangeRateResponse simulateExchangeRate(@RequestBody SimulateExchangeRate simulateExchangeRate){
        InputSimulateExchangeRate input = simulateExchangeRateMapper.mapIn(simulateExchangeRate);
        SimulateExchangeRate simulate = srvExchangeRate.simulateExchangeRate(input);
        return simulateExchangeRateMapper.mapOut(simulate);
    }

}
