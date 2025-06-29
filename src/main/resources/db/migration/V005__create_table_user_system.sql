CREATE TABLE user_system (
    id_user_system SERIAL PRIMARY KEY,
    login VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    password_hash VARCHAR NOT NULL,
    role VARCHAR NOT NULL,
    status_ativo BOOLEAN NOT NULL DEFAULT TRUE,
    id_paciente INT UNIQUE,
    id_profissional INT UNIQUE,
    data_criacao TIMESTAMP,
    data_atualizacao TIMESTAMP,
    ultimo_login TIMESTAMP,
    CONSTRAINT fk_user_paciente FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE,
    CONSTRAINT fk_user_profissional FOREIGN KEY (id_profissional) REFERENCES profissional_saude(id_profissional) ON DELETE CASCADE
);
