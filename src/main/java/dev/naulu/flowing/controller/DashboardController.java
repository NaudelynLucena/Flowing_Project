package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        // Obtén la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Convierte el principal a la entidad User
        User user = (User) authentication.getPrincipal();

        // Recupera el nombre del usuario
        String name = user.getName();

        // Construye el mensaje de bienvenida personalizado
        return "Hola, " + name + "! Bienvenido a Flowing!";
    }
}
