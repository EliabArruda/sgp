package com.eliab.sistemas.sgp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permite requisições de qualquer origem; ajuste conforme necessário.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos usados.
                .allowedHeaders("*") // Permite todos os headers.
                .allowCredentials(true) // Habilita credenciais se necessário.
                .maxAge(3600); // Tempo máximo de cache da configuração CORS.
    }
}
