package com.Uninter.VidaPlus.Repository;

import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeEntity, Long> {
}
