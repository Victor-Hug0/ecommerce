package com.project.ecommerce.models.customer;

import com.project.ecommerce.models.shared.BirthDate;
import com.project.ecommerce.models.shared.EmailAddress;
import com.project.ecommerce.models.shared.Gender;
import com.project.ecommerce.models.shared.PhoneNumber;
import jakarta.validation.constraints.Email;

public record UpdateCustomerRequestDTO(
        String firstName,
        String lastName,
        @Email
        EmailAddress email,
        PhoneNumber phoneNumber,
        BirthDate birthDate,
        Gender gender
) {
}
