CREATE TABLE address (
    id INT NOT NULL PRIMARY KEY,
    zip_code CHAR(8) NOT NULL,
    state VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    complement VARCHAR(50),
    number VARCHAR(50) NOT NULL
)