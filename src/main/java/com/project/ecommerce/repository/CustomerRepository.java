package com.project.ecommerce.repository;

import com.project.ecommerce.models.customer.Customer;
import com.project.ecommerce.models.shared.EmailAddress;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean findByEmail(@Email @NotBlank(message = "The field can´t be null, in blank or empty spaces!") EmailAddress email);
}
