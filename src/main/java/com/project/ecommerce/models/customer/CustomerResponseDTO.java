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
}
