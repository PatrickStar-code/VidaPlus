package com.Uninter.VidaPlus.Entity;

import com.Uninter.VidaPlus.Controller.Request.UserSystemRequest;
import com.Uninter.VidaPlus.Enums.RolesEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_system")
public class UserSystemEntity  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_system")
    private Long idUserSystem;

    @NotBlank
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RolesEnum role;

    @NotNull
    @Column(name = "status_ativo", nullable = false)
    private Boolean statusAtivo = true;

    @OneToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    @OneToOne
    @JoinColumn(name = "id_profissional")
    private ProfissionalSaudeEntity profissional;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    public UserSystemEntity(@Valid UserSystemRequest userSystemRequest, String passwordEncrypted) {
        this.login = userSystemRequest.login();
        this.email = userSystemRequest.email();
        this.passwordHash = passwordEncrypted;
        this.role = userSystemRequest.role();
        this.statusAtivo = userSystemRequest.statusAtivo();
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == RolesEnum.ADMINISTRADOR){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setPassword(String encode) {
        this.passwordHash = encode;
    }
}
