package dev.yagofaran.jobboardserver.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("Job Board")
                    .description("This API simplifies the interaction between companies and candidates, enabling organizations to display job positions and efficiently handle applicant profiles. Candidates can easily register within the system, sharing detailed information, including descriptions and CVs, and apply for positions with ease.")
                    .version("1")
            )
            .schemaRequirement("jwt_auth", createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
            .name("jwt_auth")
            .scheme("bearer")
            .bearerFormat("JWT")
            .type(SecurityScheme.Type.HTTP)
            .in(SecurityScheme.In.HEADER);
    }
}
