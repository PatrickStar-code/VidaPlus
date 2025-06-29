package com.Uninter.VidaPlus.Entity;

import com.Uninter.VidaPlus.Enums.GeneroEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Pessoa  {

    @NotBlank
    @Column(name = "nome", nullable = false)
    protected String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    protected GeneroEnum genero;

    @NotBlank
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    @Pattern(regexp = "\\d{11}")
    protected String cpf;

    @NotBlank
    @Column(name = "telefone", nullable = false)
    protected String telefone;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @NotBlank
    @Column(name = "bairro", nullable = false)
    protected String bairro;

    @NotBlank
    @Column(name = "rua", nullable = false)
    protected String rua;

    @NotBlank
    @Column(name = "numero", nullable = false)
    protected String numero;

    @Column(name = "complemento")
    protected String complemento;

    @NotBlank
    @Column(name = "cep", nullable = false, length = 8)
    @Pattern(regexp = "\\d{8}")
    protected String cep;
}