package com.project.ecommerce.models.shared;

import java.util.Objects;

public record EmailAddress(String value) {

    public EmailAddress {
        if (!isValid(value)) throw new IllegalArgumentException("Invalid email");
    }

    private boolean isValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress email = (EmailAddress) o;
        return Objects.equals(value, email.value);
    }

}
