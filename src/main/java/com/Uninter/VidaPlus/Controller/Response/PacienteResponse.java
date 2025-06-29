package com.Uninter.VidaPlus.Controller.Response;

import com.Uninter.VidaPlus.Enums.GeneroEnum;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record PacienteResponse(
        Long id,
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
        LocalDate dataNascimento,
        Long idUnidade,
        Boolean consentimentoLgpd,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
