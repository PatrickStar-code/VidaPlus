package com.Uninter.VidaPlus.Controller.Mapper;//package

import com.Uninter.VidaPlus.Controller.Request.PacienteRequest;
import com.Uninter.VidaPlus.Controller.Response.PacienteResponse;
import com.Uninter.VidaPlus.Entity.PacienteEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public  class PacienteMapper {

    public static PacienteResponse toPacienteResponse(PacienteEntity paciente) {
        return PacienteResponse.builder()
                .id(paciente.getIdPaciente())
                .nome(paciente.getNome())
                .genero(paciente.getGenero())
                .cpf(paciente.getCpf())
                .telefone(paciente.getTelefone())
                .email(paciente.getEmail())
                .bairro(paciente.getBairro())
                .rua(paciente.getRua())
                .numero(paciente.getNumero())
                .complemento(paciente.getComplemento())
                .cep(paciente.getCep())
                .dataNascimento(paciente.getDataNascimento())
                .idUnidade(paciente.getUnidade().getIdUnidade())
                .consentimentoLgpd(paciente.getConsentimentoLgpd())
                .dataCriacao(paciente.getDataCriacao())
                .dataAtualizacao(paciente.getDataAtualizacao())
                .build();
    }

    public static PacienteEntity toPacienteEntity(PacienteRequest paciente) {


        return PacienteEntity.builder()
                .nome(paciente.nome())
                .genero(paciente.genero())
                .cpf(paciente.cpf())
                .telefone(paciente.telefone())
                .email(paciente.email())
                .bairro(paciente.bairro())
                .rua(paciente.rua())
                .numero(paciente.numero())
                .complemento(paciente.complemento())
                .cep(paciente.cep())
                .dataNascimento(paciente.dataNascimento())
                .unidade(paciente.unidade())
                .consentimentoLgpd(paciente.consentimentoLgpd())
                .build();

    }

}
