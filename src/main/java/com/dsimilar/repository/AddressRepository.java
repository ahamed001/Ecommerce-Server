package com.dsimilar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsimilar.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
