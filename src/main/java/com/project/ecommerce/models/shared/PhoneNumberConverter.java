package com.project.ecommerce.models.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {
    @Override
    public String convertToDatabaseColumn(PhoneNumber phoneNumber) {
        return phoneNumber != null ? phoneNumber.value() : null;
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String s) {
        return s != null ? new PhoneNumber(s) : null;
    }
}
