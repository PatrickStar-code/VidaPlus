package com.Uninter.VidaPlus.Controller.Mapper;

import com.Uninter.VidaPlus.Controller.Request.ProfissionalSaudeRequest;
import com.Uninter.VidaPlus.Controller.Response.ProfissionalSaudeResponse;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfissionalSaudeMapper {

    public ProfissionalSaudeEntity toProfissionalSaudeEntity(ProfissionalSaudeRequest profissionalSaude) {
        return ProfissionalSaudeEntity.builder()
                .nome(profissionalSaude.nome())
                .genero(profissionalSaude.genero())
                .cpf(profissionalSaude.cpf())
                .telefone(profissionalSaude.telefone())
                .email(profissionalSaude.email())
                .bairro(profissionalSaude.bairro())
                .rua(profissionalSaude.rua())
                .numero(profissionalSaude.numero())
                .complemento(profissionalSaude.complemento())
                .cep(profissionalSaude.cep())
                .registroProfissional(profissionalSaude.registroProfissional())
                .especialidade(profissionalSaude.especialidade())
                .cargo(profissionalSaude.cargo())
                .unidade(profissionalSaude.unidade())
                .build();
    }

    public ProfissionalSaudeResponse toProfissionalSaudeResponse(ProfissionalSaudeEntity profissionalSaude) {
        return ProfissionalSaudeResponse.builder()
                .id(profissionalSaude.getIdProfissional())
                .nome(profissionalSaude.getNome())
                .genero(profissionalSaude.getGenero())
                .cpf(profissionalSaude.getCpf())
                .telefone(profissionalSaude.getTelefone())
                .email(profissionalSaude.getEmail())
                .bairro(profissionalSaude.getBairro())
                .rua(profissionalSaude.getRua())
                .numero(profissionalSaude.getNumero())
                .complemento(profissionalSaude.getComplemento())
                .cep(profissionalSaude.getCep())
                .registroProfissional(profissionalSaude.getRegistroProfissional())
                .especialidade(profissionalSaude.getEspecialidade())
                .cargo(profissionalSaude.getCargo())
                .unidadeId(profissionalSaude.getUnidade().getIdUnidade())
                .build();
    }
}
