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
        String email = authentication.getName();
        
        User user = authService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String message = messageService.getDailyMessage(user);
        return ResponseEntity.ok().body(
            Map.of(
                "message", message,
                "user", user.getName(),
                "status", "success"
            )
        );
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of(
                "message", e.getMessage(),
                "status", "error"
            )
        );
    }
}
}
