package com.project.ecommerce.service;

import com.project.ecommerce.models.address.Address;
import com.project.ecommerce.models.address.AddressDTO;
import com.project.ecommerce.models.address.CustomerAddress;
import com.project.ecommerce.models.customer.Customer;
import com.project.ecommerce.repository.AddressRepository;
import com.project.ecommerce.repository.CustomerAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;
    private final AddressRepository addressRepository;

    public CustomerAddressService(CustomerAddressRepository customerAddressRepository, AddressRepository addressRepository) {
        this.customerAddressRepository = customerAddressRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void createCustomerAddress(Customer customer, AddressDTO dto) {
        Address address = new Address(
                dto.zipCode(),
                dto.state(),
                dto.city(),
                dto.neighborhood(),
                dto.complement(),
                dto.street(),
                dto.number()
        );

        addressRepository.save(address);

        CustomerAddress customerAddress = new CustomerAddress(address, customer);
        customerAddressRepository.save(customerAddress);
    }

}
