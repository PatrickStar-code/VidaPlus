package com.Uninter.VidaPlus.Service;

import com.Uninter.VidaPlus.Controller.Mapper.UnidadeMapper;
import com.Uninter.VidaPlus.Controller.Request.UnidadeRequest;
import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import com.Uninter.VidaPlus.Exceptions.NotFoundException;
import com.Uninter.VidaPlus.Repository.PacienteRepository;
import com.Uninter.VidaPlus.Repository.ProfissionalSaudeRepository;
import com.Uninter.VidaPlus.Repository.UnidadeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;
    private final PacienteRepository pacienteRepository;


    public List<UnidadeEntity> getUnidades() {
        return unidadeRepository.findAll();
    }

    public Optional<UnidadeEntity> getUnidade(Long id) {
        Optional<UnidadeEntity> unidade = unidadeRepository.findById(id);

        if(unidade.isPresent()) {
            return Optional.of(unidade.get());
        }
        return Optional.empty();
    }






    @Transactional
    public UnidadeEntity create(UnidadeRequest unidade) {
            if (unidadeRepository.findByCnpjUnidade(unidade.cnpj()).isPresent()) {
                throw new RuntimeException("CNPJ da unidade já cadastrado.");
            }

            if(unidade.cep().length() != 8) {
                throw new RuntimeException("CEP inválido.");
            }

            if (unidade.profissionais() == null || unidade.profissionais().isEmpty()) {
                throw new RuntimeException("Profissionais não encontrados!");
            }

            List<ProfissionalSaudeEntity> profissionais = unidade.profissionais().stream()
                    .map(this::findProfissionalSaudeById)
                    .collect(Collectors.toList());

            List<PacienteEntity> pacientes = unidade.pacientes().stream()
                    .map(this::findPacienteById)
                    .collect(Collectors.toList());


            UnidadeEntity unidadeEntity = UnidadeMapper.toUnidadeEntity(unidade);

            unidadeEntity.setProfissionais(profissionais);

            return unidadeRepository.save(unidadeEntity);

    }

    public ProfissionalSaudeEntity findProfissionalSaudeById(ProfissionalSaudeEntity id) {
        return profissionalSaudeRepository.findById(id.getIdProfissional()).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Profissional de Saude " + id.getIdProfissional() + "nao encontrado!"));
    }

    public PacienteEntity findPacienteById(PacienteEntity id) {
        return pacienteRepository.findById(id.getIdPaciente()).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Paciente nao " + id.getIdPaciente() + " encontrado!"));
    }
}
