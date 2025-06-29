package com.Uninter.VidaPlus.Repository;

import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaudeEntity, Long> {
    Optional<ProfissionalSaudeEntity> findByEmail(String email);

    Optional<ProfissionalSaudeEntity> findByCpf(String cpf);

    Optional<ProfissionalSaudeEntity> findByRegistroProfissional(String s);
}
