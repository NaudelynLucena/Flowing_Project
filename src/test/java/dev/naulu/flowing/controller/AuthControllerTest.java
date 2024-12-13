package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import dev.naulu.flowing.service.AuthService;
import dev.naulu.flowing.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private MessageService messageService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthController authController;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setName("John Doe");
        user.setPassword("password123");
    }

    @Test
    public void testRegisterUserEmailAlreadyTaken() {
        when(authService.emailExists(user.getEmail())).thenReturn(true);

        ResponseEntity<String> response = authController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is already taken!", response.getBody());
        verify(authService, never()).registerUser(any(User.class));
    }

    @Test
    public void testRegisterUserSuccess() {
        when(authService.emailExists(user.getEmail())).thenReturn(false);

        ResponseEntity<String> response = authController.registerUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully!", response.getBody());
        verify(authService, times(1)).registerUser(user);
    }

    @SuppressWarnings("null")
    @Test
    public void testGetWelcomeMessageSuccess() {
        when(authentication.getName()).thenReturn(user.getEmail());
        when(authService.getUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(messageService.getDailyMessage(user)).thenReturn("Welcome, John Doe!");

        ResponseEntity<?> response = authController.getWelcomeMessage(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Welcome, John Doe!", body.get("message"));
        assertEquals("John Doe", body.get("user"));
        assertEquals("success", body.get("status"));
    }

    @SuppressWarnings("null")
    @Test
    public void testGetWelcomeMessageUserNotFound() {
        when(authentication.getName()).thenReturn(user.getEmail());
        when(authService.getUserByEmail(user.getEmail())).thenReturn(Optional.empty());

        ResponseEntity<?> response = authController.getWelcomeMessage(authentication);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("User not found", body.get("message"));
        assertEquals("error", body.get("status"));
    }

    @SuppressWarnings("null")
    @Test
    public void testGetWelcomeMessageException() {
        when(authentication.getName()).thenReturn(user.getEmail());
        when(authService.getUserByEmail(user.getEmail())).thenThrow(new RuntimeException("Error fetching user"));

        ResponseEntity<?> response = authController.getWelcomeMessage(authentication);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Error fetching user", body.get("message"));
        assertEquals("error", body.get("status"));
    }
}