package com.project.ecommerce.service;

import com.project.ecommerce.exception.CpfAlreadyExistsException;
import com.project.ecommerce.exception.EmailAlreadyExistsException;
import com.project.ecommerce.exception.InvalidPasswordException;
import com.project.ecommerce.models.customer.CreateCustomerRequestDTO;
import com.project.ecommerce.models.customer.Customer;
import com.project.ecommerce.models.customer.CustomerResponseDTO;
import com.project.ecommerce.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public CustomerResponseDTO createCustomer(CreateCustomerRequestDTO dto) {
        if (!dto.password().equals(dto.passwordConfirmation())) {
            throw new InvalidPasswordException("Password and confirmation do not match.");
        }

        if (customerRepository.existsByEmail(dto.email())){
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        if (customerRepository.existsByCpf(dto.cpf())) {
            throw new CpfAlreadyExistsException("CPF already exists.");
        }

        String hashPassword = bCryptPasswordEncoder.encode(dto.password());
        Customer customer = new Customer(
                dto.firstName(),
                dto.lastName(),
                dto.cpf(),
                dto.email(),
                hashPassword,
                dto.phoneNumber(),
                dto.birthDate(),
                dto.gender(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponseDTO(
                savedCustomer.getId(),
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.getEmail().value(),
                savedCustomer.getPhoneNumber().value(),
                savedCustomer.getBirthDate().value(),
                savedCustomer.getGender()
        );
    }
}
