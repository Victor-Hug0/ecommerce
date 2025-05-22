package com.project.ecommerce.models.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<EmailAddress, String> {
    @Override
    public String convertToDatabaseColumn(EmailAddress email) {
        return email != null ? email.value() : null;
    }

    @Override
    public EmailAddress convertToEntityAttribute(String s) {
        return s != null ? new EmailAddress(s) : null;
    }
}
