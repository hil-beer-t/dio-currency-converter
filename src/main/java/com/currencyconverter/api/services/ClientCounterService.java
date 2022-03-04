package com.currencyconverter.api.services;

import com.currencyconverter.api.models.ClientCounter;

public interface ClientCounterService {

    void incrementCounter();

    void decrementCounter();

    ClientCounter findById();
}
