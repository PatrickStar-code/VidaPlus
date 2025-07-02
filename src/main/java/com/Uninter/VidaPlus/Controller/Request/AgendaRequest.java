package com.Uninter.VidaPlus.Controller.Request;

import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import com.Uninter.VidaPlus.Enums.StatusAgendamentoEnum;
import com.Uninter.VidaPlus.Enums.TipoAtendimentoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record AgendaRequest(
        @NotNull
        ProfissionalSaudeEntity Profissional,

        @NotNull
        PacienteEntity Paciente,

        @NotNull
        UnidadeEntity Unidade,

        @NotNull
        LocalDateTime dataAgendamento,

        @NotNull
        LocalTime horaInicio,

        @NotNull
        LocalTime horaFim,

        @NotNull
        TipoAtendimentoEnum tipoAtendimento,

        @NotNull
        Boolean modalidadeOnline,

         StatusAgendamentoEnum statusAgendamento
) {
}
