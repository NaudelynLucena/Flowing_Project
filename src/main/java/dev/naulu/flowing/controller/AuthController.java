package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import dev.naulu.flowing.service.AuthService;
import dev.naulu.flowing.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final MessageService messageService;

    public AuthController(AuthService authService, MessageService messageService) {
        this.authService = authService;
        this.messageService = messageService;
    }

    // Ruta para mostrar el formulario de registro
    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // Se refiere a signup.html en la carpeta templates
    }

    // Ruta para procesar el formulario de registro
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user) {
        if (authService.emailExists(user.getEmail())) {
            // En caso de que el email ya exista, mostrar un mensaje de error
            return "signup"; // Devolver a la página de signup con el error
        }
        authService.registerUser(user); // Registrar al usuario
        return "redirect:/login"; // Redirigir a la página de login después de registrar
    }

    // Ruta para mostrar el mensaje de bienvenida
    @GetMapping("/welcome")
    public String getWelcomeMessage() {
        return messageService.getRandomMessage();
    }
}
