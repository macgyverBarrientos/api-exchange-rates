package com.bcp.exchangerate.dao;

import com.bcp.exchangerate.business.dto.DTOIntCreateExchangeRate;
import com.bcp.exchangerate.business.dto.DTOIntExchangeRateBuy;
import com.bcp.exchangerate.business.dto.DTOIntTargetCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetCurrencyRepository extends CrudRepository<DTOIntTargetCurrency, Long> {
    DTOIntTargetCurrency findByCurrencyAndExchangeRateAndExchangeRateBuy(String curreny, DTOIntCreateExchangeRate exchangeRate, DTOIntExchangeRateBuy exchangeRateBuy);

}
