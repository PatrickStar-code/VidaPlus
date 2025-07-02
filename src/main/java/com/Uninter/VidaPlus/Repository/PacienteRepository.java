package com.Uninter.VidaPlus.Repository;

import com.Uninter.VidaPlus.Entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Optional<PacienteEntity> findByCpf(String cpf);

    Optional<PacienteEntity> findByEmail(String email);

    boolean existsByUnidade_IdUnidade(Long idUnidade);

    List<PacienteEntity> findByUnidade_IdUnidade(Long idUnidade);
}
