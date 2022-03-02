package com.currencyconverter.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotEmpty;

@FeignClient(name = "currency", url = "https://economia.awesomeapi.com.br/json/last")
public interface CurrencyClient {

    @GetMapping(value = ("/{domainCode}"))
    String getOne(@PathVariable @NotEmpty String domainCode);

}
