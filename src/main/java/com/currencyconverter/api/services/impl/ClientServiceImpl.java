package com.currencyconverter.api.services.impl;

import com.currencyconverter.api.models.Address;
import com.currencyconverter.api.models.Client;
import com.currencyconverter.api.repository.AddressRepository;
import com.currencyconverter.api.repository.ClientRepository;
import com.currencyconverter.api.services.ClientService;
import com.currencyconverter.api.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link com.currencyconverter.api.services.ClientService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * @author falvojr
 */

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final AddressRepository addressRepository;

    private final ViaCepService viaCepService;

    public ClientServiceImpl(ClientRepository clientRepository,
                             AddressRepository addressRepository,
                             ViaCepService viaCepService) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.viaCepService = viaCepService;
    }


    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);

        return client.get();
    }

    @Override
    public void insert(Client client) {
        saveClientWithCep(client);
    }

    private void saveClientWithCep(Client client) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = client.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {

            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Address newAddress = viaCepService.resolveCep(cep);
            addressRepository.save(newAddress);

            return newAddress;
        });

        client.setAddress(address);

        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clientRepository.save(client);
    }

    @Override
    public void update(Long id, Client client) {
        // Buscar Cliente por ID, caso exista:
        Optional<Client> clientBd = clientRepository.findById(id);
        if (clientBd.isPresent()) {
            saveClientWithCep(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
