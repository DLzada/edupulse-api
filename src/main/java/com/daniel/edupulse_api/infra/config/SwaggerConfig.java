package com.daniel.edupulse_api.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Edu Pulse API")
                        .version("1.0")
                        .description("API do EduPulse: Uma plataforma inteligente para análise, monitoramento e " +
                                "auditoria da infraestrutura escolar. Permite o cálculo dinâmico de scores de conectividade," +
                                " pedagogia e bem-estar, além de oferecer ferramentas avançadas como rankings municipais, diagnósticos" +
                                " de déficit de recursos e comparação lado a lado (Versus) de instituições de ensino.")
                );
    }
}
