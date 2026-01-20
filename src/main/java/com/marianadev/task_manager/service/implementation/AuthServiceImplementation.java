package com.marianadev.task_manager.service.implementation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.marianadev.task_manager.repository.AuthRepository;
import com.marianadev.task_manager.security.jwt.JwtService;
import com.marianadev.task_manager.security.model.AuthResponse;
import com.marianadev.task_manager.service.AuthService;

import lombok.RequiredArgsConstructor;

import com.marianadev.task_manager.dto.userDto;
import com.marianadev.task_manager.mapper.userMapper;
import com.marianadev.task_manager.entity.Role;
import com.marianadev.task_manager.entity.user;


@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final AuthRepository authRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(userDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        UserDetails user= authRepository.findByUsername(userDto.getUsername()).orElseThrow();
        String token= jwtService.getToken(user);
        return  AuthResponse.builder()
                    .token(token)
                    .build();
    }

    @Override
    public AuthResponse register(userDto userDto) { 
        user userR= user.builder()
                 .username(userDto.getUsername())
                 .password(passwordEncoder.encode(userDto.getPassword()))
                 .rol(Role.USER)
                 .name(userDto.getName())
                 .lastname(userDto.getLastname())
                 .build();
        authRepository.save(userR);

        return  AuthResponse.builder()
                    .token(jwtService.getToken(userR))
                    .build();
    }   
}
