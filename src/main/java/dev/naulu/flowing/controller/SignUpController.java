package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import dev.naulu.flowing.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignUpController {

    private final AuthService authService;

    public SignUpController(AuthService authService) {
        this.authService = authService;
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
}