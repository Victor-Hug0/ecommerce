package com.project.ecommerce.models.shared;

import java.util.Objects;

public record PhoneNumber(String value) {

    public PhoneNumber {
        if (!isValid(value)) throw new IllegalArgumentException("Invalid phone number");
    }


    private boolean isValid(String phoneNumber){
        return phoneNumber.matches("^\\d{11}$\n");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(value, that.value);
    }
}
