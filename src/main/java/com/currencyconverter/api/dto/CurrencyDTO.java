package com.currencyconverter.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Pattern: DTO
 * @see <a href="https://www.baeldung.com/java-dto-pattern">DTO refecence</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class CurrencyDTO {

    public String code;
    public String codein;
    public String name;
    public String high;
    public String low;
    public String varBid;
    public String pctChange;
    public String bid;
    public String ask;
    public String timestamp;
    public String createDate;
}
