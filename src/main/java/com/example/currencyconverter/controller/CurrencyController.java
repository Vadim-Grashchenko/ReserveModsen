package com.example.currencyconverter.controller;

import com.example.currencyconverter.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@ControllerAdvice
@RequestMapping(value="/index")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currency")
    public String index(Model model) {
        model.addAttribute("currency", currencyService.getAll());
        return "index";
    }

    @PostMapping("/currency")
    public String greetingForm(@ModelAttribute("value") String value,
                               @Valid String currency,
                               Model model) {

        model.addAttribute("currency", currencyService.getAll());
        if (value.isEmpty()) {
            model.addAttribute("result", "ошибка, повторите ввод");
        } else {
            model.addAttribute("result", currencyService.outputResult(currency, Double.parseDouble(value)));
        }
        return "index";
    }
}
