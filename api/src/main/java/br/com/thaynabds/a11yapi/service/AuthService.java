package br.com.thaynabds.a11yapi.service;

import br.com.thaynabds.a11yapi.dto.AuthRequest;
import br.com.thaynabds.a11yapi.dto.AuthResponse;
import br.com.thaynabds.a11yapi.model.User;
import br.com.thaynabds.a11yapi.repository.UserRepository;
import br.com.thaynabds.a11yapi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail already registered.");
        }
        var user = new User(request.email(), passwordEncoder.encode(request.password()));
        userRepository.save(user);
        return new AuthResponse(jwtService.generateToken(request.email()), request.email());
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        return new AuthResponse(jwtService.generateToken(request.email()), request.email());
    }
}
