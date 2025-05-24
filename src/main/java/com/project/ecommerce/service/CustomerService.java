package com.project.ecommerce.service;

import com.project.ecommerce.exception.CpfAlreadyExistsException;
import com.project.ecommerce.exception.CustomerNotFoundException;
import com.project.ecommerce.exception.EmailAlreadyExistsException;
import com.project.ecommerce.exception.InvalidPasswordException;
import com.project.ecommerce.models.customer.*;
import com.project.ecommerce.models.shared.UserRole;
import com.project.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPatchMapper customerPatchMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerPatchMapper customerPatchMapper,
                           PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerPatchMapper = customerPatchMapper;
        this.passwordEncoder = passwordEncoder;
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

        String hashPassword = passwordEncoder.encode(dto.password());
        Customer customer = new Customer(
                dto.firstName(),
                dto.lastName(),
                dto.cpf(),
                dto.email(),
                hashPassword,
                dto.phoneNumber(),
                dto.birthDate(),
                dto.gender(),
                UserRole.CUSTOMER,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(savedCustomer);
    }

    public Page<CustomerResponseDTO> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(CustomerResponseDTO::fromEntity);
    }

    public CustomerResponseDTO getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
        return CustomerResponseDTO.fromEntity(customer);
    }

    public CustomerResponseDTO updateCustomer(UUID id, UpdateCustomerRequestDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        customerPatchMapper.updateCustomerFromDto(dto, customer);
        customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }

    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        customerRepository.delete(customer);
    }
}
