package com.Uninter.VidaPlus.Repository;

import com.Uninter.VidaPlus.Entity.AgendaEntity;
import com.Uninter.VidaPlus.Entity.VideoChamadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoChamadaRepository extends JpaRepository<VideoChamadaEntity, Long> {


    void deleteByIdAgenda_IdAgenda(Long idAgenda);
}
