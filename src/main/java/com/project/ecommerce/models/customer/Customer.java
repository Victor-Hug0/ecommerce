package com.project.ecommerce.models.customer;

import com.project.ecommerce.models.shared.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Convert(converter = CPFConverter.class)
    private CPF cpf;
    @Convert(converter = EmailConverter.class)
    private Email email;
    @Convert(converter = PhoneNumberConverter.class)
    @Column(name = "phone_number")
    private PhoneNumber phoneNumber;
    @Convert(converter = BirthDateConverter.class)
    private BirthDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
