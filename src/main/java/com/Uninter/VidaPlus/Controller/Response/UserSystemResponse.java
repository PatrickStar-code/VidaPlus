package com.Uninter.VidaPlus.Controller.Response;

import com.Uninter.VidaPlus.Enums.RolesEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserSystemResponse(
    Long id,
    String email,
    RolesEnum role,
    Boolean statusAtivo,
    Long idPaciente,
    Long idProfissional,
    LocalDateTime dataCriacao,
    LocalDateTime dataAtualizacao,
    LocalDateTime ultimoLogin
) {
}
