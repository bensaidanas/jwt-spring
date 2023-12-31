package com.example.jwt.auth;

import com.example.jwt.config.JwtService;
import com.example.jwt.user.Role;
import com.example.jwt.user.User;
import com.example.jwt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .isActive(true)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
//        check if the user is activated
        if (!user.isActive()) {
            throw new RuntimeException("Account is not activated");
        }
//
        var jwtToken = jwtService.generateToken(user);
//        return  AuthenticationResponse.builder().token(jwtToken).build();
        var response = AuthenticationResponse.builder()
                .token(jwtToken)
                .expirationDate(jwtService.extractExpiration(jwtToken))
                .role(user.getRole().name())
                .build();
        return response;
    }
}
