package com.itb.inf2am.pizzaria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:5174","http://localhost:5176")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Permite esses métodos
                .allowedHeaders("*")  // Permite todos os cabeçalhos
                .allowCredentials(true);  // Se estiver usando cookies ou autenticação
    }
}