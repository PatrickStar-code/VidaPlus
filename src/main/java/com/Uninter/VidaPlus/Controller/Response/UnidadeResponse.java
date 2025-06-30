package com.Uninter.VidaPlus.Controller.Response;

import com.Uninter.VidaPlus.Enums.TipoUnidadeEnum;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UnidadeResponse(
        Long id,
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
        List<Long> profissionais,
        List<Long> pacientes,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
