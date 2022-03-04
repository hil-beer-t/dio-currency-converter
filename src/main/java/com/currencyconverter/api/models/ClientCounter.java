package com.currencyconverter.api.models;

import javax.persistence.*;

@Entity
public class ClientCounter {

    @Id
    private final Long id = 1L;

    private Long numClients = 0L;

    public ClientCounter() {
    }

    public Long getNumClients() {
        return numClients;
    }

    public void incNumClients() {
        this.numClients++;
    }

    public void decrNumClients() {
        this.numClients--;
    }

    public Long getId() {
        return id;
    }
}
