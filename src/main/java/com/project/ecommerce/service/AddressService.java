package com.project.ecommerce.service;

import com.project.ecommerce.models.address.Address;
import com.project.ecommerce.models.address.AddressDTO;
import com.project.ecommerce.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = new Address(
                addressDTO.zipCode(),
                addressDTO.state(),
                addressDTO.city(),
                addressDTO.neighborhood(),
                addressDTO.complement(),
                addressDTO.street(),
                addressDTO.number()
        );

        if (addressDTO.complement() != null) {
            address.setComplement(addressDTO.complement());
        }

        addressRepository.save(address);

        return addressDTO;
    }
}
