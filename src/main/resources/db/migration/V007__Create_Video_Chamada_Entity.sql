CREATE TABLE video_chamada  (
    idVideoChamada SERIAL PRIMARY KEY,
    id_Agenda INTEGER NOT NULL,
    urlSessao VARCHAR(255) NOT NULL,
    observacao VARCHAR(255),
    FOREIGN KEY (id_Agenda) REFERENCES agenda(id_Agenda) ON DELETE CASCADE
)
