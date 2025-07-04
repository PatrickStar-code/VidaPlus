package com.Uninter.VidaPlus.Controller.Mapper;

import com.Uninter.VidaPlus.Controller.Request.VideoChamadaRequest;
import com.Uninter.VidaPlus.Controller.Response.VideoChamadaResponse;
import com.Uninter.VidaPlus.Entity.VideoChamadaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VideoChamadaMapper {

    public static VideoChamadaResponse toResponse(VideoChamadaEntity videoChamadaEntity) {
        return VideoChamadaResponse.builder()
                .idVideoChamada(videoChamadaEntity.getIdVideoChamada())
                .idAgenda(AgendaMapper.toAgendaResponse(videoChamadaEntity.getIdAgenda()))
                .urlSessao(videoChamadaEntity.getUrlSessao())
                .observacoes(videoChamadaEntity.getObservacoes())
                .build();
    }

    public static VideoChamadaEntity toEntity(VideoChamadaRequest videoChamadaRequest) {
        return VideoChamadaEntity.builder()
                .idAgenda(videoChamadaRequest.idAgenda())
                .urlSessao(videoChamadaRequest.urlSessao())
                .Observacoes(videoChamadaRequest.observacoes())
                .build();
    }

}
