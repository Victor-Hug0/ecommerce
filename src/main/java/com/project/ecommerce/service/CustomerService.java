package com.project.ecommerce.service;

import com.project.ecommerce.exception.CpfAlreadyExistsException;
import com.project.ecommerce.exception.CustomerNotFoundException;
import com.project.ecommerce.exception.EmailAlreadyExistsException;
import com.project.ecommerce.exception.InvalidPasswordException;
import com.project.ecommerce.models.address.Address;
import com.project.ecommerce.models.address.AddressDTO;
import com.project.ecommerce.models.customer.*;
import com.project.ecommerce.models.shared.UserRole;
import com.project.ecommerce.repository.AddressRepository;
import com.project.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPatchMapper customerPatchMapper;
    private final PasswordEncoder passwordEncoder;
    private final CustomerAddressService customerAddressService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerPatchMapper customerPatchMapper,
                           PasswordEncoder passwordEncoder,
                           AddressRepository addressRepository,
                           CustomerAddressService customerAddressService) {
        this.customerRepository = customerRepository;
        this.customerPatchMapper = customerPatchMapper;
        this.passwordEncoder = passwordEncoder;
        this.customerAddressService = customerAddressService;
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

    public CustomerResponseDTO vinculateAddress(AddressDTO dto){
        Customer customer = getAuthenticatedCustomer();

        customerAddressService.createCustomerAddress(customer, dto);
        customer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        return CustomerResponseDTO.fromEntity(customer);
    }

    public Customer getAuthenticatedCustomer() {
        return (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
