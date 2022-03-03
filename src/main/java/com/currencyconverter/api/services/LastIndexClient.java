package com.currencyconverter.api.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotEmpty;

/**
 * Client HTTP, criado via <b>OpenFeign</b>, para o consumo da API do
 * <b>Cotações de Moedas</b>.
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://docs.awesomeapi.com.br/api-de-moedas">Cotações de Moedas</a>
 *
 * @author hilbert
 */

@FeignClient(name = "index", url = "https://economia.awesomeapi.com.br/json/last")
public interface LastIndexClient {

    @GetMapping(value = ("/{currencyCode}"))
    String getCurrencyByCode(@PathVariable @NotEmpty String currencyCode);

}
