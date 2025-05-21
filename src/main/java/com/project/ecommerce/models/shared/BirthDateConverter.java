package com.project.ecommerce.models.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;

@Converter(autoApply = true)
public class BirthDateConverter implements AttributeConverter<BirthDate, LocalDate> {
    @Override
    public LocalDate convertToDatabaseColumn(BirthDate birthDate) {
        return birthDate != null ? birthDate.value() : null;
    }

    @Override
    public BirthDate convertToEntityAttribute(LocalDate localDate) {
        return localDate != null ? new BirthDate(localDate) : null;
    }
}
