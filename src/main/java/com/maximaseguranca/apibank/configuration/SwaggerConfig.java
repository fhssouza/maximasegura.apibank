package com.maximaseguranca.apibank.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Máxima Sistema de Segurança - API Bank")
                        .version("1.0.0")
                        .description("Teste para Desenvolvedor Back-End Java Jr")
                        .contact(new Contact()
                                .name("Fábio Heleno Souza de Souza")
                                .email("fhssouza@gmail.com"))
                );
    }

}
