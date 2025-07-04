package com.Uninter.VidaPlus.Controller.Response;

import com.Uninter.VidaPlus.Enums.StatusAgendamentoEnum;
import com.Uninter.VidaPlus.Enums.TipoAtendimentoEnum;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record AgendaResponse(
        Long idAgendamento,
        ProfissionalSaudeResponse profissionalSaude,
        PacienteResponse paciente,
        UnidadeResponse unidade,
        LocalDate dataAgendamento,
        LocalTime horaAgendamentoInicio,
        LocalTime horaAgendamentoFim,
        TipoAtendimentoEnum tipoAtendimento,
        Boolean modalidadeOnline,
        StatusAgendamentoEnum statusAgendamento
) {
}
