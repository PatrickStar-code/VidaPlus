package com.Uninter.VidaPlus.Controller.Mapper;

import com.Uninter.VidaPlus.Controller.Response.UserSystemResponse;
import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserSystemMapper {

    public static UserSystemResponse toUserResponse(UserSystemEntity user) {

        var Paciente = user.getPaciente() == null ? null : user.getPaciente().getIdPaciente();
        var Profissional = user.getProfissional() == null ? null : user.getProfissional().getIdProfissional();

        return UserSystemResponse.builder()
                .id(user.getIdUserSystem())
                .email(user.getEmail())
                .role(user.getRole())
                .statusAtivo(user.getStatusAtivo())
                .idPaciente(Paciente)
                .idProfissional(Profissional)
                .dataCriacao(user.getDataCriacao())
                .dataAtualizacao(user.getDataAtualizacao())
                .ultimoLogin(user.getUltimoLogin())
                .build();
    }

    public static UserSystemEntity toUserEntity(UserSystemResponse user) {

        return UserSystemEntity.builder()
                .idUserSystem(user.id())
                .email(user.email())
                .role(user.role())
                .statusAtivo(user.statusAtivo())
                .build();
    }

}
