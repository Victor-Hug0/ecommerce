package com.project.ecommerce.models.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return email != null ? email.value() : null;
    }

    @Override
    public Email convertToEntityAttribute(String s) {
        return s != null ? new Email(s) : null;
    }
}
