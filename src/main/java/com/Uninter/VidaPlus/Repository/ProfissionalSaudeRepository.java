package com.Uninter.VidaPlus.Repository;

import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaudeEntity, Long> {
}
