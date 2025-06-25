CREATE TABLE unidade (
    id_unidade SERIAL PRIMARY KEY,
    nome VARCHAR NOT NULL,
    cep CHAR(8) NOT NULL,
    cidade VARCHAR NOT NULL,
    estado CHAR(2) NOT NULL,
    bairro VARCHAR NOT NULL,
    rua VARCHAR NOT NULL,
    numero_unidade VARCHAR NOT NULL,
    complemento VARCHAR,
    email_unidade VARCHAR NOT NULL,
    cnpj_unidade CHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR NOT NULL,
    tipo VARCHAR NOT NULL,
    observacoes TEXT,
    data_criacao TIMESTAMP,
    data_atualizacao TIMESTAMP
);
