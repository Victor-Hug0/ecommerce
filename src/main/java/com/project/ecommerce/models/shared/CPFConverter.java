package com.project.ecommerce.models.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CPFConverter implements AttributeConverter<CPF, String> {
    @Override
    public String convertToDatabaseColumn(CPF cpf) {
        return cpf != null ? cpf.value() : null;
    }

    @Override
    public CPF convertToEntityAttribute(String s) {
        return s != null ? new CPF(s) : null;
    }
}
