package dev.naulu.flowing.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")  // 🔥 Permitir todas las rutas
            .allowedOrigins("http://localhost:3000", "https://tuapp.com")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // 🔥 Métodos permitidos
            .allowCredentials(true)  // 🔥 Permite el envío de cookies en las solicitudes
            .allowedHeaders("*"); // 🔥 Permite todos los encabezados
    }
}
