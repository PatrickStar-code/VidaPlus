package com.Uninter.VidaPlus.Service;

import com.Uninter.VidaPlus.Controller.Mapper.PacienteMapper;
import com.Uninter.VidaPlus.Controller.Request.PacienteRequest;
import com.Uninter.VidaPlus.Controller.Response.PacienteResponse;
import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Exceptions.CpfExistException;
import com.Uninter.VidaPlus.Exceptions.CpfInvalidException;
import com.Uninter.VidaPlus.Exceptions.EmailExistException;
import com.Uninter.VidaPlus.Repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;


    public List<PacienteEntity> getPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<PacienteEntity> getPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    @Transactional
    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Transactional
    public PacienteEntity registerPaciente(PacienteRequest paciente) {
        validateEmail(paciente);
        validateCpf(paciente);
        return pacienteRepository.save(PacienteMapper.toPacienteEntity(paciente));
    }

    @Transactional
    public Optional<PacienteEntity> editPaciente(Long id, PacienteRequest paciente) {
        validateEmail(paciente);
        validateCpf(paciente);

        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(id);

        if (pacienteEntity.isPresent()) {
            PacienteEntity pacienteEntity1 = pacienteEntity.get();
            pacienteEntity1.setNome(paciente.nome());
            pacienteEntity1.setGenero(paciente.genero());
            pacienteEntity1.setCpf(paciente.cpf());
            pacienteEntity1.setTelefone(paciente.telefone());
            pacienteEntity1.setEmail(paciente.email());
            pacienteEntity1.setBairro(paciente.bairro());
            pacienteEntity1.setRua(paciente.rua());
            pacienteEntity1.setNumero(paciente.numero());
            pacienteEntity1.setComplemento(paciente.complemento());
            pacienteEntity1.setCep(paciente.cep());
            pacienteEntity1.setDataNascimento(paciente.dataNascimento());
            pacienteEntity1.setUnidade(paciente.unidade());
            pacienteEntity1.setDataAtualizacao(LocalDateTime.now());
            pacienteEntity1.setConsentimentoLgpd(paciente.consentimentoLgpd());
            return Optional.of(pacienteRepository.save(pacienteEntity1));
        }
        return  Optional.empty();
    }

    private void validateCpf(PacienteRequest paciente) {
        if (paciente.cpf() != null && paciente.cpf().length() == 11) {
            Optional<PacienteEntity> cpfNotNull = pacienteRepository.findByCpf(paciente.cpf());
            if (cpfNotNull.isPresent()) {
                throw new CpfExistException("CPF_EXIST", "CPF ja cadastrado!");
            }
        }
        else{
            throw new CpfInvalidException("INVALID_ERROR", "CPF invalido!");
        }
    }

    private void validateEmail(PacienteRequest paciente) {
        if (paciente.email() != null) {
            Optional<PacienteEntity> emailNotNull = pacienteRepository.findByEmail(paciente.email());
            if (emailNotNull.isPresent()) {
                throw new EmailExistException("EMAIL_EXIST", "Email ja cadastrado!");
            }
        }
    }
}
