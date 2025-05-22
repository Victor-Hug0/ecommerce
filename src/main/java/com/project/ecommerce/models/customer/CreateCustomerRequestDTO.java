package com.project.ecommerce.models.customer;

import com.project.ecommerce.models.shared.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequestDTO(
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        String firstName,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        String lastName,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        CPF cpf,
        @Email
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        EmailAddress email,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        @Size(min = 8, message = "Password must be 8 characters at least!")
        String password,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        String passwordConfirmation,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        PhoneNumber phoneNumber,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        BirthDate birthDate,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        Gender gender
) {
}
