package com.project.ecommerce.controller;

import com.project.ecommerce.infra.security.TokenResponseDTO;
import com.project.ecommerce.infra.security.TokenService;
import com.project.ecommerce.models.address.AddressDTO;
import com.project.ecommerce.models.customer.*;
import com.project.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public CustomerController(CustomerService customerService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid CustomerAuthRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((Customer) authentication.getPrincipal());

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(token, tokenService.getExpirationTimeInSeconds());
        return ResponseEntity.ok(tokenResponseDTO);
    }

    @PostMapping("/register")
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

    @PostMapping("/address")
    public ResponseEntity<CustomerResponseDTO> vinculateAddress(@RequestBody @Valid AddressDTO dto) {
        CustomerResponseDTO customerResponseDTO = customerService.vinculateAddress(dto);
        return ResponseEntity.ok(customerResponseDTO);
    }
}
