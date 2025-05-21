package com.project.ecommerce.models.shared;

import java.time.LocalDate;
import java.util.Objects;

public record BirthDate(LocalDate value) {

    public BirthDate {
        if (value == null) throw new IllegalArgumentException("Invalid birth date");

        if (value.isAfter(LocalDate.now())) throw new IllegalArgumentException("Invalid birth date");
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
