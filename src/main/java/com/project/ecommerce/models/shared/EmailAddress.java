package com.project.ecommerce.models.shared;

import java.util.Objects;

public record Email(String value) {

    public Email {
        if (!isValid(value)) throw new IllegalArgumentException("Invalid email");
    }

    private boolean isValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

}
