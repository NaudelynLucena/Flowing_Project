package dev.naulu.flowing.service;

import dev.naulu.flowing.model.User;
import dev.naulu.flowing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
    }

    @Test
    public void testLoadUserByUsernameFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(java.util.Optional.of(user));

        UserDetails result = userService.loadUserByUsername(user.getEmail());

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getUsername());
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(java.util.Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(user.getEmail());
        });

        assertEquals("User not found: " + user.getEmail(), exception.getMessage());
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void testGetUserIdByUsernameFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(java.util.Optional.of(user));

        Long userId = userService.getUserIdByUsername(user.getEmail());

        assertEquals(user.getId(), userId);
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void testGetUserIdByUsernameNotFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(java.util.Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserIdByUsername(user.getEmail());
        });

        assertEquals("User not found: " + user.getEmail(), exception.getMessage());
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void testFindUserByIdFound() {
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));

        User result = userService.findUserById(user.getId());

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository).findById(user.getId());
    }

    @Test
    public void testFindUserByIdNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.findUserById(user.getId());
        });

        assertEquals("User not found with id: " + user.getId(), exception.getMessage());
        verify(userRepository).findById(user.getId());
    }
}
