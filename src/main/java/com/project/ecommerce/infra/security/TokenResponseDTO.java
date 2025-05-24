package com.project.ecommerce.infra.security;

public record TokenResponseDTO(
        String token,
        Integer expirationTime
) {
}
