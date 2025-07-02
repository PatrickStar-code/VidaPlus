package com.Uninter.VidaPlus.Service;

import com.Uninter.VidaPlus.Controller.Mapper.AgendaMapper;
import com.Uninter.VidaPlus.Controller.Request.AgendaRequest;
import com.Uninter.VidaPlus.Controller.Response.AgendaResponse;
import com.Uninter.VidaPlus.Entity.AgendaEntity;
import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import com.Uninter.VidaPlus.Exceptions.NotFoundException;
import com.Uninter.VidaPlus.Repository.AgendaRepository;
import com.Uninter.VidaPlus.Repository.PacienteRepository;
import com.Uninter.VidaPlus.Repository.ProfissionalSaudeRepository;
import com.Uninter.VidaPlus.Repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;
    private final UnidadeRepository unidadeRepository;

    public List<AgendaEntity> getAgenda() {
        return agendaRepository.findAll();
    }

    public Optional<AgendaEntity> getAgendaById(Long id) {
        return Optional.ofNullable(agendaRepository.findById(id).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Agenda nao encontrada!")));
    }

    public void deleteAgenda(Long id) {
        agendaRepository.deleteById(id);
    }

    public AgendaEntity registerAgenda(AgendaRequest agenda) {
        // Validar os IDs
        if (agenda.Paciente() == null || agenda.Paciente().getIdPaciente() == null)
            throw new NotFoundException("NOT_FOUND", "Paciente nao encontrado!");

        if (agenda.Profissional() == null || agenda.Profissional().getIdProfissional() == null)
            throw new NotFoundException("NOT_FOUND", "Profissional de Saude nao encontrado!");

        if (agenda.Unidade() == null || agenda.Unidade().getIdUnidade() == null)
            throw new NotFoundException("NOT_FOUND", "Unidade nao encontrada!");

        // Buscar os objetos do banco para garantir que estão completos
        PacienteEntity paciente = pacienteRepository.findById(agenda.Paciente().getIdPaciente())
                .orElseThrow(() -> new NotFoundException("NOT_FOUND", "Paciente não encontrado"));

        ProfissionalSaudeEntity profissional = profissionalSaudeRepository.findById(agenda.Profissional().getIdProfissional())
                .orElseThrow(() -> new NotFoundException("NOT_FOUND", "Profissional não encontrado"));

        UnidadeEntity unidade = unidadeRepository.findById(agenda.Unidade().getIdUnidade())
                .orElseThrow(() -> new NotFoundException("NOT_FOUND", "Unidade não encontrada"));

        // Criar manualmente a entidade (evitando passar objetos incompletos)
        AgendaEntity entity = AgendaEntity.builder()
                .data(agenda.dataAgendamento())
                .horaInicio(agenda.horaInicio())
                .horaFim(agenda.horaFim())
                .tipoAtendimento(agenda.tipoAtendimento())
                .modalidadeOnline(agenda.modalidadeOnline())
                .status(agenda.statusAgendamento())
                .profissional(profissional)
                .paciente(paciente)
                .unidade(unidade)
                .build();

        return agendaRepository.save(entity);
    }

    private PacienteEntity findPacienteById(PacienteEntity id) {
        return pacienteRepository.findById(id.getIdPaciente()).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Paciente nao " + id.getIdPaciente() + " encontrado!"));
    }

    private ProfissionalSaudeEntity findProfissionalSaudeById(ProfissionalSaudeEntity id) {
        return  profissionalSaudeRepository.findById(id.getIdProfissional()).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Profissional de Saude " + id.getIdProfissional() + "nao encontrado!"));
    }

    private UnidadeEntity findUnidadeById(UnidadeEntity id) {
        return  unidadeRepository.findById(id.getIdUnidade()).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Unidade " + id.getIdUnidade() + " nao encontrada!"));
    }
}
