package dev.naulu.flowing.service;

import dev.naulu.flowing.model.User;
import dev.naulu.flowing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    private User user;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authService = new AuthService(userRepository, passwordEncoder);

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
    }

    @Test
    public void testEmailExistsTrue() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        boolean result = authService.emailExists(user.getEmail());

        assertTrue(result);
        verify(userRepository).existsByEmail(user.getEmail());
    }

    @Test
    public void testEmailExistsFalse() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        boolean result = authService.emailExists(user.getEmail());

        assertFalse(result);
        verify(userRepository).existsByEmail(user.getEmail());
    }

    @Test
    public void testRegisterUser() {
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        authService.registerUser(user);

        assertEquals("encodedPassword", user.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    public void testGetUserByIdFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<User> result = authService.getUserById(user.getId());

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository).findById(user.getId());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        Optional<User> result = authService.getUserById(user.getId());

        assertFalse(result.isPresent());
        verify(userRepository).findById(user.getId());
    }

    @Test
    public void testGetUserByEmailFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Optional<User> result = authService.getUserByEmail(user.getEmail());

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void testGetUserByEmailNotFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        Optional<User> result = authService.getUserByEmail(user.getEmail());

        assertFalse(result.isPresent());
        verify(userRepository).findByEmail(user.getEmail());
    }
}
