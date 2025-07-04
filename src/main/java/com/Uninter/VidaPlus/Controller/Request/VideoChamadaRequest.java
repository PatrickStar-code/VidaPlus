package com.Uninter.VidaPlus.Controller.Request;

import com.Uninter.VidaPlus.Entity.AgendaEntity;
import lombok.Builder;

@Builder
public record VideoChamadaRequest(
        AgendaEntity idAgenda,
        String urlSessao,
        String observacoes
) {
}
