package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DashboardControllerTest {

    @Mock
    private Authentication authentication;

    @InjectMocks
    private DashboardController dashboardController;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("John Doe");
    }

    @Test
    public void testDashboardWhenNotAuthenticated() {
        when(authentication.isAuthenticated()).thenReturn(false);

        String response = dashboardController.dashboard(authentication);

        assertEquals("Acceso no autorizado.", response);
    }

    @Test
    public void testDashboardWhenAuthenticated() {
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(user);

        String response = dashboardController.dashboard(authentication);

        assertEquals("Hola, John Doe! Bienvenido a Flowing!", response);
    }

    @Test
    public void testDashboardWhenAuthenticationIsNull() {
        String response = dashboardController.dashboard(null);

        assertEquals("Acceso no autorizado.", response);
    }
}
