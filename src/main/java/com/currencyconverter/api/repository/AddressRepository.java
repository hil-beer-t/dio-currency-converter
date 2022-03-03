package com.currencyconverter.api.repository;

import com.currencyconverter.api.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}