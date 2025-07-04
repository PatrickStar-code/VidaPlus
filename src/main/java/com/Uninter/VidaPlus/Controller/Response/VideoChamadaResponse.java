package com.Uninter.VidaPlus.Controller.Response;

import lombok.Builder;

@Builder
public record VideoChamadaResponse (
         Long idVideoChamada,
         AgendaResponse idAgenda,
         String urlSessao,
         String observacoes
){
}
