package com.project.ecommerce.models.customer;

import com.project.ecommerce.models.shared.Gender;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,
        String fistName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        Gender gender
) {

    public static CustomerResponseDTO fromEntity(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail().value(),
                customer.getPhoneNumber().value(),
                customer.getBirthDate().value(),
                customer.getGender()
        );
    }

}
