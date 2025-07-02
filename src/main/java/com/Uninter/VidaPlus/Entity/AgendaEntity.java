package com.Uninter.VidaPlus.Entity;

import com.Uninter.VidaPlus.Enums.StatusAgendamentoEnum;
import com.Uninter.VidaPlus.Enums.TipoAtendimentoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agenda")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda")
    private Long idAgenda;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_profissional", nullable = false)
    private ProfissionalSaudeEntity profissional;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private UnidadeEntity unidade;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @NotNull
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @NotNull
    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_atendimento", nullable = false)
    private TipoAtendimentoEnum tipoAtendimento;

    @NotNull
    @Column(name = "modalidade_online", nullable = false)
    private Boolean modalidadeOnline = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAgendamentoEnum status;
}