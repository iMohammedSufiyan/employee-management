package com.sufiyan.employee_management.service.impl;

import com.sufiyan.employee_management.dto.AuthRequestDto;
import com.sufiyan.employee_management.dto.AuthResponseDto;
import com.sufiyan.employee_management.dto.GenericResponse;
import com.sufiyan.employee_management.dto.RegisterRequestDto;
import com.sufiyan.employee_management.entity.Role;
import com.sufiyan.employee_management.entity.User;
import com.sufiyan.employee_management.repository.UserRepository;
import com.sufiyan.employee_management.security.JwtService;
import com.sufiyan.employee_management.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @Override
    public GenericResponse<AuthResponseDto> register(RegisterRequestDto request) {

        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return GenericResponse.badRequest("Email already registered");
        }

        // Create new user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : Role.USER)
                .build();

        userRepository.save(user);
        log.info("New user registered: {}", request.getEmail());

        // Generate token
        String token = jwtService.generateToken(user);

        return GenericResponse.success(
                "User registered successfully",
                AuthResponseDto.builder()
                        .token(token)
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .build()
        );
    }

    @Override
    public GenericResponse<AuthResponseDto> login(AuthRequestDto request) {

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Load user and generate token
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(user);
        log.info("User logged in: {}", request.getEmail());

        return GenericResponse.success(
                "Login successful",
                AuthResponseDto.builder()
                        .token(token)
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .build()
        );
    }
}