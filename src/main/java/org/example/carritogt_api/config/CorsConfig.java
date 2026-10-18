package org.example.carritogt_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** CORS explícito (RNF-07): permite que un frontend en otro origen consuma la API. */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500",
                        "http://localhost:8080", "http://carrito-compra.localhost")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
