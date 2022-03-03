package com.currencyconverter.api.services;

import org.springframework.http.ResponseEntity;

public interface ConversionService {
    ResponseEntity<String> convertCurrency(String currencyCode, Double amount);
}
