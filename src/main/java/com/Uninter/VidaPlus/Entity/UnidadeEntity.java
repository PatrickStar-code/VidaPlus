package com.Uninter.VidaPlus.Entity;

import com.Uninter.VidaPlus.Enums.TipoUnidadeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "unidade")
public class UnidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidade")
    private Long idUnidade;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    @Column(name = "cep", nullable = false, length = 8)
    private String cep;

    @NotBlank
    @Column(name = "cidade", nullable = false)
    private String cidade;

    @NotBlank
    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @NotBlank
    @Column(name = "bairro", nullable = false)
    private String bairro;

    @NotBlank
    @Column(name = "rua", nullable = false)
    private String rua;

    @NotBlank
    @Column(name = "numero_unidade", nullable = false)
    private String numeroUnidade;

    @Column(name = "complemento")
    private String complemento;

    @Email
    @NotBlank
    @Column(name = "email_unidade", nullable = false)
    private String emailUnidade;

    @NotBlank
    @Column(name = "cnpj_unidade", nullable = false, unique = true, length = 14)
    @Pattern(regexp = "\\d{14}")
    private String cnpjUnidade;

    @NotBlank
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoUnidadeEnum tipo;

    @Column(name = "observacoes")
    private String observacoes;

    @OneToMany(mappedBy = "unidade")
    private List<PacienteEntity> pacientes;

    @OneToMany(mappedBy = "unidade")
    private List<ProfissionalSaudeEntity> profissionais;

    @CreatedDate
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}