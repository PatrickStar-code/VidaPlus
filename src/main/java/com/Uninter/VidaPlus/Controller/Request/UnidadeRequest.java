package com.Uninter.VidaPlus.Controller.Request;

import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Enums.TipoUnidadeEnum;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UnidadeRequest(
        String nome,
        String cep,
        String cidade,
        String estado,
        String bairro,
        String rua,
        String numero,
        String complemento,
        String emailUnidade,
        String cnpj,
        String telefone,
        TipoUnidadeEnum tipo,
        List<PacienteEntity> pacientes,
        List<ProfissionalSaudeEntity> profissionais,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
