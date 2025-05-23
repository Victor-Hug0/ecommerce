package com.project.ecommerce.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.ecommerce.exception.InvalidBirthDateException;

import java.time.LocalDate;
import java.util.Objects;

public record BirthDate(LocalDate value) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public BirthDate(@JsonProperty("value") LocalDate value) {
        if (value == null || value.isAfter(LocalDate.now())) throw new InvalidBirthDateException("Invalid birth date");

        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BirthDate birthDate = (BirthDate) o;
        return Objects.equals(value, birthDate.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
