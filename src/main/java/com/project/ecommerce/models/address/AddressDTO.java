package com.project.ecommerce.models.address;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
        @NotBlank
        String zipCode,
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotBlank
        String neighborhood,
        @NotBlank
        String street,
        String complement,
        @NotBlank
        String number
) {
        public static AddressDTO fromEntity(Address address) {
                return new AddressDTO(
                        address.getZipcode(),
                        address.getState(),
                        address.getCity(),
                        address.getNeighborhood(),
                        address.getStreet(),
                        address.getComplement(),
                        address.getNumber()
                );
        }
}
