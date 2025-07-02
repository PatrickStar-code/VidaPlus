package com.Uninter.VidaPlus.Controller.Mapper;

import com.Uninter.VidaPlus.Controller.Request.AgendaRequest;
import com.Uninter.VidaPlus.Controller.Response.AgendaResponse;
import com.Uninter.VidaPlus.Entity.AgendaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AgendaMapper {

    public AgendaResponse toAgendaResponse(AgendaEntity agenda) {
        return AgendaResponse.builder()
                .profissionalSaude(ProfissionalSaudeMapper.toProfissionalSaudeResponse(agenda.getProfissional()))
                .paciente(PacienteMapper.toPacienteResponse(agenda.getPaciente()))
                .unidade(UnidadeMapper.toUnidadeReesponse(agenda.getUnidade()))
                .dataAgendamento(agenda.getData().toLocalDate())
                .horaAgendamentoInicio(agenda.getHoraInicio())
                .horaAgendamentoFim(agenda.getHoraFim())
                .tipoAtendimento(agenda.getTipoAtendimento())
                .modalidadeOnline(agenda.getModalidadeOnline())
                .statusAgendamento(agenda.getStatus())
                .build();
    }

    public AgendaEntity toAgendaEntity(AgendaRequest agenda) {
        return AgendaEntity.builder()
                .data(agenda.dataAgendamento())
                .horaInicio(agenda.horaInicio())
                .horaFim(agenda.horaFim())
                .tipoAtendimento(agenda.tipoAtendimento())
                .modalidadeOnline(agenda.modalidadeOnline())
                .status(agenda.statusAgendamento())
                .profissional(agenda.Profissional())
                .paciente(agenda.Paciente())
                .unidade(agenda.Unidade())
                .build();
    }

}
