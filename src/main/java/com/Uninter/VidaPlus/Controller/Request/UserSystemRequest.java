package com.Uninter.VidaPlus.Controller.Request;

import com.Uninter.VidaPlus.Enums.RolesEnum;

public record UserSystemRequest(
    String login,
    String email,
    String passwordHash,
    RolesEnum role,
    Boolean statusAtivo,
    Long idPaciente,
    Long idProfissional
) {
}
