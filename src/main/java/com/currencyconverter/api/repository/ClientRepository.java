package com.currencyconverter.api.repository;

import com.currencyconverter.api.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}