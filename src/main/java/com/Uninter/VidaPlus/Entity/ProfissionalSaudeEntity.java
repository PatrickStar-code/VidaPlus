package com.Uninter.VidaPlus.Entity;

import com.Uninter.VidaPlus.Enums.CargoSaudeEnum;
import com.Uninter.VidaPlus.Enums.EspecialidadeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profissional_saude")
public class ProfissionalSaudeEntity extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profissional")
    private Long idProfissional;

    @NotBlank
    @Column(name = "registro_profissional", nullable = false, unique = true)
    private String registroProfissional;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade", nullable = false)
    private EspecialidadeEnum especialidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private CargoSaudeEnum cargo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private UnidadeEntity unidade;

    @CreatedDate
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;


}