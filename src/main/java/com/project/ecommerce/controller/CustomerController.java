package com.project.ecommerce.controller;

import com.project.ecommerce.models.customer.CreateCustomerRequestDTO;
import com.project.ecommerce.models.customer.CustomerResponseDTO;
import com.project.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CreateCustomerRequestDTO dto) {

        CustomerResponseDTO customerResponseDTO = customerService.createCustomer(dto);

        return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);
    }
}
