package com.bcp.exchangerate.business.impl;

import com.bcp.exchangerate.business.ISrvExchangeRate;
import com.bcp.exchangerate.business.dto.*;
import com.bcp.exchangerate.dao.ExchangeRateRepository;
import com.bcp.exchangerate.dao.TargetCurrencyRepository;
import com.bcp.exchangerate.model.BaseAmount;
import com.bcp.exchangerate.model.ExchangeRate;
import com.bcp.exchangerate.model.SimulateExchangeRate;
import com.bcp.exchangerate.model.TargetAmount;
import com.bcp.exchangerate.util.properties.Properties;
import com.bcp.exchangerate.util.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SrvExchangeRate implements ISrvExchangeRate {
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private TargetCurrencyRepository targetCurrencyRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private Properties properties;
    @Override
    public DTOIntCreateExchangeRate createExchangeRate(InputCreateExchangeRate input) {
        validator.validate(input);
        List<DTOIntTargetCurrency> targetCurrencies = input.getCreateExchangeRate().getTargetCurrencies();
        input.getCreateExchangeRate().setTargetCurrencies(null);
        DTOIntCreateExchangeRate exchangeRate = exchangeRateRepository.save(input.getCreateExchangeRate());
        exchangeRate.setTargetCurrencies(new ArrayList<>());
        for (DTOIntTargetCurrency intTargetCurrency: targetCurrencies) {
            intTargetCurrency.setExchangeRate(exchangeRate);
            DTOIntTargetCurrency currency = targetCurrencyRepository.save(intTargetCurrency);
            exchangeRate.getTargetCurrencies().add(currency);
        }
        return exchangeRate;
    }

    @Override
    public DTOIntCreateExchangeRate getTrade(final Long idExchangeRate) {
        return exchangeRateRepository.findById(idExchangeRate).orElse(null);
    }

    @Override
    public Boolean existExchangeRate(final Long idExchangeRate) {
        //return exchangeRateRepository.existsById(idExchangeRate);
        return null;
    }

    @Override
    public List<DTOIntCreateExchangeRate> listExchangeRates(final InputListExchangeRates input) {
        if (input==null){
            return (List<DTOIntCreateExchangeRate>) exchangeRateRepository.findAll();
        }
        if (input.getCurrency()!=null && input.getOrigin()==null){
            return exchangeRateRepository.findAllByCurrency(input.getCurrency());
        }
        if (input.getCurrency()==null && input.getOrigin()!=null){
            return exchangeRateRepository.findAllByOrigin(input.getOrigin());
        }
        return exchangeRateRepository.findAllByCurrencyAndOrigin(input.getCurrency(),input.getOrigin());
    }

    @Override
    public SimulateExchangeRate simulateExchangeRate(final InputSimulateExchangeRate input) {
        //validaciones
        DTOIntCreateExchangeRate dtoIntCreateExchangeRate = exchangeRateRepository.findByCurrencyAndOrigin(input.getSimulateExchangeRate().getBaseAmount().getCurrency(),
                input.getSimulateExchangeRate().getOrigin());
        dtoIntCreateExchangeRate.setTargetCurrencies(dtoIntTargetCurrencies(dtoIntCreateExchangeRate.getTargetCurrencies(),input.getSimulateExchangeRate().getTargetCurrencies()));

        return getSimulateExchangeRate(input, dtoIntCreateExchangeRate);
    }
    private List<DTOIntTargetCurrency> dtoIntTargetCurrencies(final List<DTOIntTargetCurrency> targetCurrencyList, String divisas){
        if (CollectionUtils.isEmpty(targetCurrencyList)){
            return null;
        }
        return targetCurrencyList.stream().filter(dtoIntTargetCurrency ->divisas.contains(dtoIntTargetCurrency.getCurrency())).collect(Collectors.toList());
    }

    private SimulateExchangeRate getSimulateExchangeRate(final InputSimulateExchangeRate input , final DTOIntCreateExchangeRate exchangeRate){
        SimulateExchangeRate simulateExchangeRate = new SimulateExchangeRate();
        simulateExchangeRate.setBaseAmount(getBaseAmount(input.getSimulateExchangeRate().getBaseAmount()));
        simulateExchangeRate.setOrigin(input.getSimulateExchangeRate().getOrigin());
        simulateExchangeRate.setTargetAmounts(getTargetAmountList(exchangeRate.getTargetCurrencies(),input.getSimulateExchangeRate().getExchangeRate(),input.getSimulateExchangeRate().getOrigin(),input.getSimulateExchangeRate().getBaseAmount().getAmount()));
        return simulateExchangeRate;
    }
    private BaseAmount getBaseAmount(final DTOIntBaseAmount dtoIntBaseAmount){
        if (dtoIntBaseAmount == null){
            return null;
        }
        BaseAmount baseAmount = new BaseAmount();
        baseAmount.setCurrency(dtoIntBaseAmount.getCurrency());
        baseAmount.setAmount(dtoIntBaseAmount.getAmount());
        return baseAmount;
    }
    private List<TargetAmount> getTargetAmountList(final List<DTOIntTargetCurrency> dtoIntTargetCurrencies, DTOIntExchangeRate dtoIntExchangeRate, String origin, BigDecimal amountOperation){
        return dtoIntTargetCurrencies.stream().filter(Objects::nonNull).map(dtoIntTargetCurrency -> getTargetAmount(dtoIntTargetCurrency,dtoIntExchangeRate,origin,amountOperation )).collect(Collectors.toList());
    }
    private TargetAmount getTargetAmount(DTOIntTargetCurrency dtoIntTargetCurrency, DTOIntExchangeRate dtoIntExchangeRate, String origin, BigDecimal amountOperation){
        TargetAmount targetAmount = new TargetAmount();
        targetAmount.setCurrency(dtoIntTargetCurrency.getCurrency());
        targetAmount.setExchangeRate(getExchangeRate(dtoIntTargetCurrency, dtoIntExchangeRate));
        targetAmount.setLastExchangeUpdate(dtoIntTargetCurrency.getLastExchangeUpdate());
        BigDecimal operationCommissionPercentage = new BigDecimal(properties.getProperty(origin+ ".operation.commission."+dtoIntTargetCurrency.getCurrency()));
        targetAmount.setNetAmount(amountOperation.multiply(targetAmount.getExchangeRate().getValue()));
        targetAmount.setFeeAmount(operationCommissionPercentage.multiply(amountOperation));
        targetAmount.setTotalAmount(targetAmount.getNetAmount().subtract(targetAmount.getFeeAmount()));
        return targetAmount;
    }

    private ExchangeRate getExchangeRate(DTOIntTargetCurrency dtoIntTargetCurrency, DTOIntExchangeRate dtoIntExchangeRate){
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setAction(dtoIntExchangeRate.getAction());
        if ("FIXING_BUY".equalsIgnoreCase(dtoIntExchangeRate.getAction())){
            exchangeRate.setValue(dtoIntTargetCurrency.getExchangeRateBuy().getValue());
        }
        if ("FIXING_SELL".equalsIgnoreCase(dtoIntExchangeRate.getAction())){
            exchangeRate.setValue(dtoIntTargetCurrency.getExchangeRateSell().getValue());
        }
        return exchangeRate;
    }
}
