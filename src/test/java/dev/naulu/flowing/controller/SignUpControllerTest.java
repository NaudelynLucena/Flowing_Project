package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import dev.naulu.flowing.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SignUpControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private SignUpController signUpController;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
    }

    @Test
    public void testRegisterUserEmailAlreadyTaken() {
        when(authService.emailExists(user.getEmail())).thenReturn(true);

        ResponseEntity<String> response = signUpController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is already taken!", response.getBody());

        verify(authService, never()).registerUser(any(User.class));
    }

    @Test
    public void testRegisterUserSuccess() {
        when(authService.emailExists(user.getEmail())).thenReturn(false);

        ResponseEntity<String> response = signUpController.registerUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully!", response.getBody());

        verify(authService, times(1)).registerUser(user);
    }
}
