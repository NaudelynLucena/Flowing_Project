package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import dev.naulu.flowing.service.AuthService;
import dev.naulu.flowing.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final MessageService messageService;

    public AuthController(AuthService authService, MessageService messageService) {
        this.authService = authService;
        this.messageService = messageService;
    }

    @PostMapping("/signup")
public ResponseEntity<String> registerUser(@RequestBody User user) {
    if (authService.emailExists(user.getEmail())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Email is already taken!");
    }
    authService.registerUser(user);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body("User registered successfully!");
}

    @GetMapping("/welcome")
public ResponseEntity<?> getWelcomeMessage(Authentication authentication) {
    try {
        // 🔥 Obtiene el email del usuario autenticado
        String email = authentication.getName();
        
        // 🔥 Busca al usuario por email
        User user = authService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 Genera el mensaje diario
        String message = messageService.getDailyMessage(user);

        // 🔥 Retorna la respuesta de bienvenida
        return ResponseEntity.ok().body(
            Map.of(
                "message", message,
                "user", user.getName(),
                "status", "success"
            )
        );
    } catch (RuntimeException e) {
        // Si hay error, responde con un 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of(
                "message", e.getMessage(),
                "status", "error"
            )
        );
    }
}
}
