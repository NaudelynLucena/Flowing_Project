package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "Acceso no autorizado.";
        }
        // Convierte el principal a la entidad User
        User user = (User) authentication.getPrincipal();

        // Recupera el nombre del usuario
        String name = user.getName();

        // Construye el mensaje de bienvenida personalizado
        return "Hola, " + name + "! Bienvenido a Flowing!";
    }
}
