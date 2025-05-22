package com.project.ecommerce.exception;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        int status,
        String message,
        String path,
        LocalDateTime timestamp
) {
}
