package com.project.ecommerce.controller;

import com.project.ecommerce.models.customer.CreateCustomerRequestDTO;
import com.project.ecommerce.models.customer.CustomerResponseDTO;
import com.project.ecommerce.models.customer.UpdateCustomerRequestDTO;
import com.project.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

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

        URI location = URI.create("/customer/" + customerResponseDTO.id());

        return ResponseEntity.created(location).body(customerResponseDTO);
    }

    @GetMapping
    public Page<CustomerResponseDTO> getCustomers(Pageable pageable) {
        return customerService.getCustomers(pageable);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable UUID id) {
        URI location = URI.create("/customer/" + id);
        return ResponseEntity.created(location).body(customerService.getCustomerById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable UUID id, @Valid @RequestBody UpdateCustomerRequestDTO dto) {
        CustomerResponseDTO customerResponseDTO = customerService.updateCustomer(id, dto);
        URI location = URI.create("/customer/" + customerResponseDTO.id());
        return ResponseEntity.created(location).body(customerResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
