package com.eam.skillforge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Plataforma de Capacitación Online - SkillForge",
        version = "1.0.0",
        description = "API RESTful para la gestión de un plataforma de capacitación online",
        contact = @Contact(
            name = "Construcción de Apps Empresariales",
            email = "dev@eam.edu.co",
            url = "eam.edu.co"
        ),
        license = @License(
            name = "MIT Licence",
            url = "https://opensource.org/licenses/MIT"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080",
            description = "Servidor de desarrollo"
        ),
        @Server(
            url = "http://localhost:5432",
            description = "Servidor de pruebas"
        ),
        @Server(
            url = "http://localhost:49668",
            description = "Servidor de producción"
        )
    }
)

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentification",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Ingresa tu token JWT")
                        )
                );
    }
}
