package com.currencyconverter.api.controllers;

import com.currencyconverter.api.models.Client;
import com.currencyconverter.api.services.ClientCounterService;
import com.currencyconverter.api.services.ClientService;
import com.currencyconverter.api.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Implementação de um <b>Observer</b> simples {@link ConversionService},
 * incrementa o número de clientes no banco sempre que é criado um novo cliente.
 * @author hilbert
 */

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    private final ClientCounterService clientCounterService;

    public ClientController(ClientService clientService, ClientCounterService clientCounterService) {
        this.clientService = clientService;
        this.clientCounterService = clientCounterService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client client) {
        clientService.insert(client);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        clientService.update(id, client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

}
