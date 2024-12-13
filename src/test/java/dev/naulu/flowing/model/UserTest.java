package dev.naulu.flowing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("test@example.com", "password123", "Test User");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("test@example.com", user.getEmail(), "Email should match the constructor value");
        assertEquals("password123", user.getPassword(), "Password should match the constructor value");
        assertEquals("Test User", user.getName(), "Name should match the constructor value");
    }

    @Test
    void testSetters() {
        user.setEmail("newemail@example.com");
        user.setPassword("newpassword123");
        user.setName("New User");

        assertEquals("newemail@example.com", user.getEmail(), "Email should be updated");
        assertEquals("newpassword123", user.getPassword(), "Password should be updated");
        assertEquals("New User", user.getName(), "Name should be updated");
    }

    @Test
    void testGetUsername() {
        assertEquals("test@example.com", user.getUsername(), "Username should match the email");
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size(), "There should be exactly one authority");
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")), "Authority should be ROLE_USER");
    }

    @Test
    void testAccountNonExpired() {
        assertTrue(user.isAccountNonExpired(), "Account should be non-expired by default");
    }

    @Test
    void testAccountNonLocked() {
        assertTrue(user.isAccountNonLocked(), "Account should be non-locked by default");
    }

    @Test
    void testCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired(), "Credentials should be non-expired by default");
    }

    @Test
    void testIsEnabled() {
        assertTrue(user.isEnabled(), "User should be enabled by default");
    }

    @Test
    void testEqualsAndHashCode() {
        User user2 = new User("test@example.com", "password123", "Test User");
        user2.setPassword("differentPassword");

        assertEquals(user, user2, "Users with the same email should be considered equal");
        assertEquals(user.hashCode(), user2.hashCode(), "Users with the same email should have the same hash code");

        user2.setEmail("different@example.com");
        assertNotEquals(user, user2, "Users with different emails should not be equal");
    }

    @Test
    void testDefaultConstructor() {
        User emptyUser = new User();
        assertNull(emptyUser.getEmail(), "Default constructor should initialize email as null");
        assertNull(emptyUser.getPassword(), "Default constructor should initialize password as null");
        assertNull(emptyUser.getName(), "Default constructor should initialize name as null");
    }
}