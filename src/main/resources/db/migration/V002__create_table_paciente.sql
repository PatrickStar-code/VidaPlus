CREATE TABLE paciente (
    id_paciente SERIAL PRIMARY KEY,
    nome VARCHAR NOT NULL,
    genero VARCHAR NOT NULL,
    cpf CHAR(11) UNIQUE NOT NULL,
    telefone VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    bairro VARCHAR NOT NULL,
    rua VARCHAR NOT NULL,
    numero VARCHAR NOT NULL,
    complemento VARCHAR,
    cep CHAR(8) NOT NULL,
    data_nascimento DATE NOT NULL,
    id_unidade INT NOT NULL,
    consentimento_lgpd BOOLEAN NOT NULL DEFAULT FALSE,
    data_criacao TIMESTAMP,
    data_atualizacao TIMESTAMP,
    CONSTRAINT fk_paciente_unidade FOREIGN KEY (id_unidade) REFERENCES unidade(id_unidade)
);
