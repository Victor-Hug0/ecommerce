CREATE TABLE customer_addresses (
                                    customer_id UUID NOT NULL,
                                    address_id INT NOT NULL,
                                    PRIMARY KEY (address_id),
                                    FOREIGN KEY (customer_id) REFERENCES customers(id),
                                    FOREIGN KEY (address_id) REFERENCES address(id)
);
