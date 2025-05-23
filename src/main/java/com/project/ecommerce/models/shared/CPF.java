package com.project.ecommerce.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.ecommerce.exception.InvalidCpfException;

import java.util.Objects;

public record CPF(String value) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public CPF(@JsonProperty("value") String value) {
        if (!isValidCPF(value)) throw new InvalidCpfException("Invalid CPF");
        this.value = value;
    }

    public boolean isValidCPF(String cpf) {
        return cpf != null && cpf.matches("^\\d{11}$");
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(value, cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
