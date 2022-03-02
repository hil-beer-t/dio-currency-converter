package com.currencyconverter.api.controllers;

import com.currencyconverter.api.client.CurrencyClient;
import com.currencyconverter.api.dto.CurrencyDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Locale;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyClient currencyClient;

    @GetMapping(value = ("/{domainCode}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOne(@PathVariable @NotEmpty String domainCode, @RequestParam Double amount){

        String currencyResponse = currencyClient.getOne(domainCode);

        String domainCurrencyCodeResolved = domainCode.replace("-","");

        JSONObject unmappedCurrencyObject = new JSONObject(currencyResponse);

        CurrencyDTO currencyDTO = mapResponse(domainCurrencyCodeResolved, unmappedCurrencyObject);

        return resolveExchange(Double.parseDouble(currencyDTO.getAsk()),
                amount);
    }

    private CurrencyDTO mapResponse(String dCr, JSONObject obj) {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCode(obj.getJSONObject(dCr).getString("code"));
        currencyDTO.setCodein(obj.getJSONObject(dCr).getString("codein"));
        currencyDTO.setName(obj.getJSONObject(dCr).getString("name"));
        currencyDTO.setHigh(obj.getJSONObject(dCr).getString("high"));
        currencyDTO.setLow(obj.getJSONObject(dCr).getString("low"));
        currencyDTO.setVarBid(obj.getJSONObject(dCr).getString("varBid"));
        currencyDTO.setPctChange(obj.getJSONObject(dCr).getString("pctChange"));
        currencyDTO.setBid(obj.getJSONObject(dCr).getString("bid"));
        currencyDTO.setAsk(obj.getJSONObject(dCr).getString("ask"));
        return currencyDTO;
    }

    private ResponseEntity<String> resolveExchange(Double ask, Double amount){

        return ResponseEntity.ok("{\"response\":"+String.format(Locale.ROOT,"%.2f",ask*amount)+"}");
    }
}
