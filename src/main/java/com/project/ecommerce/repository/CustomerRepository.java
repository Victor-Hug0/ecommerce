package com.project.ecommerce.repository;

import com.project.ecommerce.models.customer.Customer;
import com.project.ecommerce.models.shared.CPF;
import com.project.ecommerce.models.shared.EmailAddress;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsByEmail(@Email @NotNull(message = "The field can´t be null, in blank or empty spaces!") EmailAddress email);
    boolean existsByCpf(@NotNull(message = "The field can´t be null!") CPF cpf);

    UserDetails findByEmail(EmailAddress email);
}
