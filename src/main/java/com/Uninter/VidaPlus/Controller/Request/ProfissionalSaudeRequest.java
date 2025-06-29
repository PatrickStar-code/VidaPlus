package com.Uninter.VidaPlus.Controller.Request;

import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import com.Uninter.VidaPlus.Enums.CargoSaudeEnum;
import com.Uninter.VidaPlus.Enums.EspecialidadeEnum;
import com.Uninter.VidaPlus.Enums.GeneroEnum;
import lombok.Builder;

@Builder
public record ProfissionalSaudeRequest(
        String nome,
        GeneroEnum genero,
        String cpf,
        String telefone,
        String email,
        String bairro,
        String rua,
        String numero,
        String complemento,
        String cep,
        String registroProfissional,
        EspecialidadeEnum especialidade,
        CargoSaudeEnum cargo,
        UnidadeEntity unidade
) {

}
