package com.Uninter.VidaPlus.Repository;

import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSystemRepository extends JpaRepository<UserSystemEntity, Long> {
    Optional<UserSystemEntity> findByLoginIgnoreCase(String email);

    Optional<UserSystemEntity> findByEmailIgnoreCase(String email);
    
    Optional<UserSystemEntity> findByProfissionalIdProfissional(Long Long);

    Optional<UserSystemEntity> findByPacienteIdPaciente(Long aLong);
}
