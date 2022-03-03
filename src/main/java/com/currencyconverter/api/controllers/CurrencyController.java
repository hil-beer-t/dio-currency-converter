package com.currencyconverter.api.controllers;

import com.currencyconverter.api.services.ConversionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private final ConversionService currencyConverter;

    @GetMapping(value = "/{currencyCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convertCurrency(@PathVariable String currencyCode, Double amount){
        return currencyConverter.convertCurrency(currencyCode, amount);
    }

}
