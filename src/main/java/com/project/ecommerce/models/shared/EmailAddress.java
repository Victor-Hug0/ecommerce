package com.project.ecommerce.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.ecommerce.exception.InvalidEmailException;

import java.util.Objects;

public record EmailAddress(String value) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public EmailAddress(@JsonProperty("value") String value) {
        if (!isValid(value)) throw new InvalidEmailException("Invalid email");
        this.value = value;
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
