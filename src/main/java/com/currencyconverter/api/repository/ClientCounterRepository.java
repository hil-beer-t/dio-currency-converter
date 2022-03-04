package com.currencyconverter.api.repository;

import com.currencyconverter.api.models.ClientCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCounterRepository extends JpaRepository<ClientCounter, Long> {
}