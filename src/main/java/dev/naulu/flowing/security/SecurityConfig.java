package dev.naulu.flowing.security;

import dev.naulu.flowing.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/signup").permitAll() // Rutas públicas
                    .requestMatchers("/dashboard", "/api/auth/welcome").authenticated() // El dashboard es solo para usuarios autenticados
                    .requestMatchers("/api/activities/**").authenticated() // Proteger todas las rutas de actividades
                    .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
                )
                .formLogin(form -> form
                    .loginProcessingUrl("/login") // Procesar login desde esta URL
                    .defaultSuccessUrl("/dashboard", true) // Redirigir al dashboard tras iniciar sesión
                    .failureUrl("/login?error=true") // Redirigir al login con ?error=true si falla
                    .permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
