package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import dev.naulu.flowing.service.AuthService;
import dev.naulu.flowing.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<?> getWelcomeMessage() {
        try {
            // 🔥 Obtiene el usuario autenticado desde el contexto de seguridad
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            // 🔥 Obtiene el usuario desde la base de datos
            User user = authService.getUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // 🔥 Genera el mensaje diario
            String message = messageService.getDailyMessage(user);

            // 🔥 Retorna una respuesta clara con el mensaje
            return ResponseEntity.ok().body(
                Map.of(
                    "message", message,
                    "user", user.getEmail(),
                    "status", "success"
                )
            );
        } catch (RuntimeException e) {
            // Si hay un error, responde con un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "message", e.getMessage(),
                    "status", "error"
                )
            );
        }
    }
}
