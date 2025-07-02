package com.Uninter.VidaPlus.Service;

import com.Uninter.VidaPlus.Controller.Mapper.ProfissionalSaudeMapper;
import com.Uninter.VidaPlus.Controller.Request.ProfissionalSaudeRequest;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Exceptions.CpfInvalidException;
import com.Uninter.VidaPlus.Exceptions.InvalidArgumentException;
import com.Uninter.VidaPlus.Exceptions.ValueExistException;
import com.Uninter.VidaPlus.Repository.ProfissionalSaudeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfissionalSaudeService {

    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    public List<ProfissionalSaudeEntity> getProfissionalSaude() {
        return profissionalSaudeRepository.findAll();
    }

    public Optional<ProfissionalSaudeEntity> getProfissionalSaudeById(Long id) {
        return profissionalSaudeRepository.findById(id);
    }

    @Transactional
    public void deleteProfissionalSaude(Long id) {
        profissionalSaudeRepository.deleteById(id);
    }

    @Transactional
    public ProfissionalSaudeEntity registerProfissionalSaude(ProfissionalSaudeRequest request) {
        if (request.unidade() == null) {
            throw new NullPointerException("Unidade nao pode ser nula!");
        }

        validateRegistroProfissional(request);
        validateEmail(request);
        validateCpf(request);
        validateCep(request);


        ProfissionalSaudeEntity entity = ProfissionalSaudeMapper.toProfissionalSaudeEntity(request);
        entity.setDataCriacao(LocalDateTime.now());
        return profissionalSaudeRepository.save(entity);
    }

    @Transactional
    public Optional<ProfissionalSaudeEntity> editProfissionalSaude(Long id, ProfissionalSaudeRequest request) {
        Optional<ProfissionalSaudeEntity> profissional = profissionalSaudeRepository.findById(id);

        if (request.cep().length() != 8) {
            throw new InvalidArgumentException("INVALID_ERROR", "CEP invalido!");
        }

        if (request.cpf().length() != 11) {
            throw new InvalidArgumentException("INVALID_ERROR", "CPF invalido!");
        }

        if (profissional.isPresent()) {
            ProfissionalSaudeEntity entity = profissional.get();
            entity.setUnidade(request.unidade());
            entity.setNome(request.nome());
            entity.setCpf(request.cpf());
            entity.setRegistroProfissional(request.registroProfissional());
            entity.setEmail(request.email());
            entity.setTelefone(request.telefone());
            entity.setBairro(request.bairro());
            entity.setRua(request.rua());
            entity.setNumero(request.numero());
            entity.setComplemento(request.complemento());
            entity.setCep(request.cep());
            entity.setGenero(request.genero());
            entity.setEspecialidade(request.especialidade());
            entity.setCargo(request.cargo());

            return Optional.of(profissionalSaudeRepository.save(entity));
        }

        return Optional.empty();
    }

    private void validateCpf(ProfissionalSaudeRequest request) {
        if (request.cpf() != null && request.cpf().length() == 11) {
            Optional<ProfissionalSaudeEntity> exists = profissionalSaudeRepository.findByCpf(request.cpf());
            if (exists.isPresent()) {
                throw new ValueExistException("CPF_EXIST", "CPF já cadastrado!");
            }
        } else {
            throw new CpfInvalidException("INVALID_ERROR", "CPF inválido!");
        }
    }

    private void validateEmail(ProfissionalSaudeRequest request) {
        if (request.email() != null) {
            Optional<ProfissionalSaudeEntity> exists = profissionalSaudeRepository.findByEmail(request.email());
            if (exists.isPresent()) {
                throw new ValueExistException("EMAIL_EXIST", "Email já cadastrado!");
            }
        }
    }

    private void validateRegistroProfissional(ProfissionalSaudeRequest request) {
        if (request.registroProfissional() != null) {
            Optional<ProfissionalSaudeEntity> exists = profissionalSaudeRepository.findByRegistroProfissional(request.registroProfissional());
            if (exists.isPresent()) {
                throw new ValueExistException("REGISTRO_EXIST", "Registro Profissional já cadastrado!");
            }
        }
    }

    private void validateCep(ProfissionalSaudeRequest request) {
        if (request.cep() != null && request.cep().length() != 8) {
            throw new InvalidArgumentException("INVALID_ERROR", "CEP inválido!");
        }
    }
}
