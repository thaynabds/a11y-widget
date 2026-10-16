package br.com.thaynabds.a11yapi.service;

import br.com.thaynabds.a11yapi.dto.AuthRequest;
import br.com.thaynabds.a11yapi.model.User;
import br.com.thaynabds.a11yapi.repository.UserRepository;
import br.com.thaynabds.a11yapi.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock UserRepository userRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock JwtService jwtService;
    @Mock AuthenticationManager authenticationManager;

    @InjectMocks AuthService authService;

    @BeforeEach
    void setUp() {
        when(passwordEncoder.encode(anyString())).thenReturn("hashed");
        when(jwtService.generateToken(anyString())).thenReturn("jwt-token");
    }

    @Test
    void register_shouldReturnToken_whenEmailIsNew() {
        when(userRepository.existsByEmail("test@test.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        var result = authService.register(new AuthRequest("test@test.com", "password123"));

        assertThat(result.token()).isEqualTo("jwt-token");
        assertThat(result.email()).isEqualTo("test@test.com");
    }

    @Test
    void register_shouldThrow_whenEmailAlreadyExists() {
        when(userRepository.existsByEmail("exists@test.com")).thenReturn(true);

        assertThatThrownBy(() -> authService.register(new AuthRequest("exists@test.com", "password123")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("already registered");
    }

    @Test
    void login_shouldReturnToken_onValidCredentials() {
        var result = authService.login(new AuthRequest("user@test.com", "password123"));
        assertThat(result.token()).isEqualTo("jwt-token");
    }
}
