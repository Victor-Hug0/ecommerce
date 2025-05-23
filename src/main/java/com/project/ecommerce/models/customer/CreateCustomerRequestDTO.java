package com.project.ecommerce.models.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.ecommerce.models.shared.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequestDTO(
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        String firstName,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        String lastName,
        @NotNull(message = "The field can´t be null!")
        CPF cpf,
        @Email
        @NotNull(message = "The field can´t be null!")
        EmailAddress email,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        @Size(min = 8, message = "Password must be 8 characters at least!")
        String password,
        @NotBlank(message = "The field can´t be null, in blank or empty spaces!")
        String passwordConfirmation,
        @NotNull(message = "The field can´t be null!")
        PhoneNumber phoneNumber,
        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "The field can´t be null!")
        BirthDate birthDate,
        @NotNull(message = "The field can´t be null!")
        Gender gender
) {
}
