package com.project.ecommerce.models.shared;

import java.util.Objects;

public record CPF(String value) {

    public CPF {
        if (!isValidCPF(value)) throw new IllegalArgumentException("Invalid CPF");
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
