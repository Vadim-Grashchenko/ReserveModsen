package com.example.currencyconverter.service;

import com.example.currencyconverter.entity.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {
    List<Currency> getAll();
    List<Double> calculate(String name, double a);
    List<Currency> getOtherCurrency(String name);
    String outputResult(String name, double a);
}
