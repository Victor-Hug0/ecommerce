package com.project.ecommerce.repository;

import com.project.ecommerce.models.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
