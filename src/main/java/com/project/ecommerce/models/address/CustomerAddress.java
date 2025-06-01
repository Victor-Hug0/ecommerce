package com.project.ecommerce.models.address;

import com.project.ecommerce.models.customer.Customer;
import jakarta.persistence.*;

@Entity
@Table(name = "customer_addresses")
public class CustomerAddress {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public CustomerAddress(Address address, Customer customer) {
        this.address = address;
        this.customer = customer;
    }

    public CustomerAddress() {
    }

    public Address getAddress() {
        return address;
    }
}
