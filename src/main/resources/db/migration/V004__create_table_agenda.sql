CREATE TABLE agenda (
    id_agenda SERIAL PRIMARY KEY,
    id_profissional INT NOT NULL,
    id_paciente INT NOT NULL,
    id_unidade INT NOT NULL,
    data TIMESTAMP NOT NULL,
    hora_inicio TIMESTAMP NOT NULL,
    hora_fim TIMESTAMP NOT NULL,
    tipo_atendimento VARCHAR NOT NULL,
    modalidade_online BOOLEAN NOT NULL DEFAULT FALSE,
    status VARCHAR NOT NULL,
    CONSTRAINT fk_agenda_profissional FOREIGN KEY (id_profissional) REFERENCES profissional_saude(id_profissional),
    CONSTRAINT fk_agenda_paciente FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    CONSTRAINT fk_agenda_unidade FOREIGN KEY (id_unidade) REFERENCES unidade(id_unidade)
);
