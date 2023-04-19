package com.example.currencyconverter.service;

import com.example.currencyconverter.entity.Currency;
import com.example.currencyconverter.repository.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ImplCurrencyService implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    @Override
    public List<Double> calculate(String name, double a) {
        List<Currency> listOtherCurrency = getOtherCurrency(name);
        double c = currencyRepository.findAll().stream()
                .filter(i -> Objects.equals(i.getName(), name))
                .findFirst()
                .get()
                .getSale() * a;
        return listOtherCurrency.stream()
                .map(i -> c / i.getSale())
                .toList();
    }
    
    @Override
    public List<Currency> getOtherCurrency(String name) {
        return currencyRepository.findAll().stream()
                .filter(i -> !Objects.equals(i.getName(), name))
                .toList();
    }

    @Override
    public String outputResult(String name, double a) {
        DecimalFormat df = new DecimalFormat("###.###");
        List<Double> listResult = calculate(name, a);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < listResult.size(); i++) {
            result.append(df.format(listResult.get(i))).append(" ").append(getOtherCurrency(name).get(i).getName()).append("\r\n");
        }
        return result.toString();
    }
    
}
