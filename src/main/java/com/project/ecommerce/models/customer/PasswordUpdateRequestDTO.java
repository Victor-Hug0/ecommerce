package com.project.ecommerce.models.customer;

public record PasswordUpdateRequestDTO(
        String currentPassword,
        String newPassword,
        String newPasswordConfirmation
) {
}
