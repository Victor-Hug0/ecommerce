package com.project.ecommerce.models.customer;

public record CustomerAuthRequestDTO(
        String email,
        String password
) {
}
