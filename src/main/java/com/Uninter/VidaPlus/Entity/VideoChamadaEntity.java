package com.Uninter.VidaPlus.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video_chamada")
@Entity
public class VideoChamadaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvideochamada")
    private Long idVideoChamada;

    @OneToOne
    @JoinColumn(name = "id_agenda")
    private AgendaEntity idAgenda;

    @Column(name = "urlsessao")
    private String urlSessao;

    @Column(name = "observacao")
    private String Observacoes;

}
