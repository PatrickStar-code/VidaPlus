package com.Uninter.VidaPlus.Config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final FilterTokenJWT filterTokenJWT;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Rotas públicas (sem autenticação)
                        .requestMatchers(
                                "/api/api-docs/**",
                                "/swagger/**",
                                "/login",
                                "/refresh-token",
                                "/vidaPlus/user/register"
                        ).permitAll()

                        // Acesso ADMIN e USER: acessos gerais que ambos podem ver (mas não alterar)
                        .requestMatchers(
                                "/vidaPlus/agenda/{id}",
                                "/vidaPlus/agenda",
                                "/vidaPlus/user/{id}",
                                "/vidaPlus/unidade",
                                "/vidaPlus/unidade/{id}",
                                "/vidaPlus/paciente/{id}",
                                "/vidaPlus/profissional-saude/{id}",
                                "/vidaPlus/videochamada/{id}"
                        ).hasAnyRole("ADMIN", "USER")

                        // Apenas USER (ex: criação de agenda)
                        .requestMatchers(
                                "/vidaPlus/agenda"
                        ).hasRole("USER")

                        // Apenas ADMIN (administração total)
                        .requestMatchers(
                                "/vidaPlus/agenda/**",
                                "/vidaPlus/user/**",
                                "/vidaPlus/paciente/**",
                                "/vidaPlus/profissional-saude/**",
                                "/vidaPlus/unidade/**",
                                "/vidaPlus/videochamada"
                        ).hasRole("ADMIN")

                        // Qualquer outra requisição precisa estar autenticada com qualquer papel
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filterTokenJWT, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder encriptador() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
