CREATE TABLE customers(

                          id           UUID PRIMARY KEY UNIQUE,
                          first_name   VARCHAR(50)  NOT NULL,
                          last_name    VARCHAR(50)  NOT NULL,
                          email        VARCHAR(100) NOT NULL UNIQUE,
                          password     VARCHAR(255) NOT NULL,
                          phone_number char(11),
                          cpf          char(11)  NOT NULL UNIQUE,
                          gender       CHAR(1),
                          role         VARCHAR(20) NOT NULL,
                          created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
)