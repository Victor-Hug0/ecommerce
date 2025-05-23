package com.project.ecommerce.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.ecommerce.exception.InvalidPhoneNumberException;

import java.util.Objects;

public record PhoneNumber(String value) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public PhoneNumber(@JsonProperty("value") String value) {
        if (!isValid(value)) throw new InvalidPhoneNumberException("Invalid phone number");
        this.value = value;
    }


    private static boolean isValid(String phoneNumber){
        return phoneNumber.matches("^\\d{11}$");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(value, that.value);
    }
}
