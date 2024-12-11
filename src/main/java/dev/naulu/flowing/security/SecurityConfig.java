package dev.naulu.flowing.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliza BCrypt para cifrado de contraseñas
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request -> 
            request
                    .requestMatchers("/api/signup").permitAll() // Permitir registro sin autenticar
                    .requestMatchers("/dashboard", "/api/auth/welcome").authenticated() // Rutas protegidas
                    .requestMatchers("/api/activities/**").hasRole("USER") // Solo acceso para usuarios con rol USER
                    .requestMatchers("/api/mood/**").hasRole("USER") // Solo acceso para usuarios con rol USER
                    .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
            )
            //.formLogin(Customizer.withDefaults());
        //http
            .httpBasic(Customizer.withDefaults())
            //.formLogin(Customizer.withDefaults())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
            );

        http.sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.cors(Customizer.withDefaults());

        http.exceptionHandling(Customizer.withDefaults());

        return http.build();
    }
}