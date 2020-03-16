package com.bcp.exchangerate.dao;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.business.dto.DTOIntTargetCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends CrudRepository<DTOIntCreateExchangeRate, Long> {
    List<DTOIntCreateExchangeRate> findAllByCurrencyAndOrigin(String currency, String origin);
    List<DTOIntCreateExchangeRate> findAllByCurrencyOrOrigin(String currency, String origin);
    List<DTOIntCreateExchangeRate> findAllByCurrency(String currency);
    List<DTOIntCreateExchangeRate> findAllByOrigin(String origin);
    DTOIntCreateExchangeRate findByCurrencyAndOrigin(String currency, String origin);
    List<DTOIntCreateExchangeRate> findAllByCurrencyAndOriginAndTargetCurrencies(String currency, String origin, List<DTOIntTargetCurrency> targetCurrencies);
}
