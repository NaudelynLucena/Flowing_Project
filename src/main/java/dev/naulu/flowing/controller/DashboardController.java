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
        
        User user = (User) authentication.getPrincipal();
        String name = user.getName();
        return "Hola, " + name + "! Bienvenido a Flowing!";
    }
}
