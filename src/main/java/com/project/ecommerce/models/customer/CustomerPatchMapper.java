package com.project.ecommerce.models.customer;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerPatchMapper {
    void updateCustomerFromDto(UpdateCustomerRequestDTO updateCustomerRequestDTO, @MappingTarget Customer customer);
}
