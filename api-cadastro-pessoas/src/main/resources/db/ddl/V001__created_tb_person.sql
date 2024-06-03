CREATE TABLE tb_person
(
    id         VARCHAR(36)  NOT NULL,
    cpf        VARCHAR(11)  NOT NULL UNIQUE,
    name       VARCHAR(80)  NOT NULL,
    date_of_birth date         NOT NULL,
    email      VARCHAR(255) NULL,
    phone      VARCHAR(255) NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NULL,
    CONSTRAINT pk_tb_person PRIMARY KEY (id)
);

ALTER TABLE tb_person
    ADD CONSTRAINT uc_tb_person_cpf UNIQUE (cpf);