package com.Uninter.VidaPlus.Controller.Response;

import com.Uninter.VidaPlus.Enums.StatusAgendamentoEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendaResponse(
        ProfissionalSaudeResponse profissionalSaude,
        PacienteResponse paciente,
        UnidadeResponse unidade,
        LocalDate dataAgendamento,
        LocalTime horaAgendamentoInicio,
        LocalTime horaAgendamentoFim,
        Boolean modalidadeOnline,
        StatusAgendamentoEnum statusAgendad
){

}
