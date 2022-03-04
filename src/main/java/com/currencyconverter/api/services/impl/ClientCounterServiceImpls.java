package com.currencyconverter.api.services.impl;

import com.currencyconverter.api.models.ClientCounter;
import com.currencyconverter.api.repository.ClientCounterRepository;
import com.currencyconverter.api.services.ClientCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientCounterServiceImpls implements ClientCounterService {

    @Autowired
    ClientCounterRepository clientCounterRepository;

    @Override
    public void incrementCounter() {

        ClientCounter clientCounter = findById();
        clientCounter.incNumClients();
        clientCounterRepository.save(clientCounter);

    }

    @Override
    public void decrementCounter() {
        ClientCounter clientCounter = findById();
        clientCounter.decrNumClients();
        clientCounterRepository.save(clientCounter);
    }

    @Override
    public ClientCounter findById() {
        Optional<ClientCounter> clientCounterOpt = Optional.of(clientCounterRepository.findById(1L).orElseGet(() -> {
            ClientCounter firstClientCounter = new ClientCounter();
            clientCounterRepository.save(firstClientCounter);
            return firstClientCounter;
        }));

        return clientCounterOpt.get();
    }
}
