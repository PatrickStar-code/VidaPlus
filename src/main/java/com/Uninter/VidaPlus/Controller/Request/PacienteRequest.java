package com.Uninter.VidaPlus.Controller.Request;

import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import com.Uninter.VidaPlus.Enums.GeneroEnum;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record PacienteRequest(
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
        UnidadeEntity unidade,
        Boolean consentimentoLgpd,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {

}
