package com.project.ecommerce.models.customer;

import com.project.ecommerce.models.address.AddressDTO;
import com.project.ecommerce.models.address.CustomerAddress;
import com.project.ecommerce.models.shared.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record CustomerResponseDTO(
        UUID id,
        String fistName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        Gender gender,
        List<AddressDTO> addresses
) {

    public static CustomerResponseDTO fromEntity(Customer customer) {
        List<AddressDTO> addressDTOS = customer.getCustomerAddresses().stream()
                .map(CustomerAddress::getAddress)
                .map(AddressDTO::fromEntity)
                .toList();

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail().value(),
                customer.getPhoneNumber().value(),
                customer.getBirthDate().value(),
                customer.getGender(),
                addressDTOS
        );
    }

}
