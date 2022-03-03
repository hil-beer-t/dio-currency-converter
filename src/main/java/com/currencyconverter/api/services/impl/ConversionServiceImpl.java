package com.currencyconverter.api.services.impl;

import com.currencyconverter.api.dto.CurrencyDTO;
import com.currencyconverter.api.services.ConversionService;
import com.currencyconverter.api.services.LastIndexClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Implementação da <b>Strategy</b> {@link ConversionService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * @author hilbert
 */

@Service
public class ConversionServiceImpl implements ConversionService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    private final LastIndexClient lastIndexClient;

    public ConversionServiceImpl(LastIndexClient lastIndexClient) {
        this.lastIndexClient = lastIndexClient;
    }

    @Override
    public ResponseEntity<String> convertCurrency(String currencyCode, Double amount) {

        String unmappedCurrency = lastIndexClient.getCurrencyByCode(currencyCode);

        String currencyCodeFormatted = formatCurrency(currencyCode);

        JSONObject unmappedJsonCurrency = new JSONObject(unmappedCurrency);

        CurrencyDTO currencyDTO = mapCurrency(currencyCodeFormatted, unmappedJsonCurrency);

        String currencyFormattedAndConverted = String.format(Locale.ROOT,
                "%.2f",
                currencyDTO.getAskDouble()*amount);

        return ResponseEntity.ok("{\"response\":"+currencyFormattedAndConverted+"}");
    }

    private String formatCurrency(String currencyCode) {
        return currencyCode.replace("-","");
    }

    private CurrencyDTO mapCurrency(String uC, JSONObject obj) {

        CurrencyDTO currencyDTO = new CurrencyDTO();

        currencyDTO.setCode(obj.getJSONObject(uC).getString("code"));
        currencyDTO.setCodein(obj.getJSONObject(uC).getString("codein"));
        currencyDTO.setName(obj.getJSONObject(uC).getString("name"));
        currencyDTO.setHigh(obj.getJSONObject(uC).getString("high"));
        currencyDTO.setLow(obj.getJSONObject(uC).getString("low"));
        currencyDTO.setVarBid(obj.getJSONObject(uC).getString("varBid"));
        currencyDTO.setPctChange(obj.getJSONObject(uC).getString("pctChange"));
        currencyDTO.setBid(obj.getJSONObject(uC).getString("bid"));
        currencyDTO.setAsk(obj.getJSONObject(uC).getString("ask"));
        currencyDTO.setAskDouble(Double.valueOf(currencyDTO.getAsk()));

        return currencyDTO;
    }

}
