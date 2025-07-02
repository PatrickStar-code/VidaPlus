package com.Uninter.VidaPlus.Controller.Mapper;


import com.Uninter.VidaPlus.Controller.Request.UnidadeRequest;
import com.Uninter.VidaPlus.Controller.Response.UnidadeResponse;
import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UnidadeMapper {

    public static UnidadeEntity toUnidadeEntity(UnidadeRequest unidadeRequest) {
        return UnidadeEntity.builder()
                .nome(unidadeRequest.nome())
                .cep(unidadeRequest.cep())
                .cidade(unidadeRequest.cidade())
                .estado(unidadeRequest.estado())
                .bairro(unidadeRequest.bairro())
                .rua(unidadeRequest.rua())
                .numeroUnidade(unidadeRequest.numero())
                .complemento(unidadeRequest.complemento())
                .emailUnidade(unidadeRequest.emailUnidade())
                .cnpjUnidade(unidadeRequest.cnpj())
                .telefone(unidadeRequest.telefone())
                .tipo(unidadeRequest.tipo())
                .dataCriacao(unidadeRequest.dataCriacao())
                .dataAtualizacao(unidadeRequest.dataAtualizacao())
                .pacientes(unidadeRequest.pacientes())
                .profissionais(unidadeRequest.profissionais())
                .build();
    }

    public static UnidadeResponse toUnidadeReesponse(UnidadeEntity unidadeEntity) {

        List<Long> pacientes = unidadeEntity.getPacientes().stream().map(PacienteEntity::getIdPaciente).toList();
        List<Long> profissionais = unidadeEntity.getProfissionais().stream().map(ProfissionalSaudeEntity::getIdProfissional).toList();

        return UnidadeResponse.builder()
                .id(unidadeEntity.getIdUnidade())
                .nome(unidadeEntity.getNome())
                .cep(unidadeEntity.getCep())
                .cidade(unidadeEntity.getCidade())
                .estado(unidadeEntity.getEstado())
                .bairro(unidadeEntity.getBairro())
                .rua(unidadeEntity.getRua())
                .numero(unidadeEntity.getNumeroUnidade())
                .complemento(unidadeEntity.getComplemento())
                .emailUnidade(unidadeEntity.getEmailUnidade())
                .cnpj(unidadeEntity.getCnpjUnidade())
                .telefone(unidadeEntity.getTelefone())
                .tipo(unidadeEntity.getTipo())
                .dataCriacao(unidadeEntity.getDataCriacao())
                .dataAtualizacao(unidadeEntity.getDataAtualizacao())
                .pacientes(pacientes)
                .profissionais(profissionais)
                .build();
 }
}
