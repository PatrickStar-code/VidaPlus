package com.Uninter.VidaPlus.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenApi() {

        // Informações da API
        Contact contact = new Contact()
                .name("Patrick")
                .email("patrick.almeida06@gmail.com");

        Info info = new Info()
                .title("Vida Plus API")
                .version("v1")
                .description("Projeto para Vida Plus")
                .contact(contact);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Insira o token JWT no formato: Bearer {token}");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement) // Aplica a segurança globalmente
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme));
    }
}
