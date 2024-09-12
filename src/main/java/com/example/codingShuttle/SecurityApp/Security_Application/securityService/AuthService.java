package com.example.codingShuttle.SecurityApp.Security_Application.securityService;

import com.example.codingShuttle.SecurityApp.Security_Application.dto.LoginDto;
import com.example.codingShuttle.SecurityApp.Security_Application.dto.LoginResponseDto;
import com.example.codingShuttle.SecurityApp.Security_Application.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
   private final SessionService sessionService;

    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
       sessionService.generateNewSession(user, refreshToken);

        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        UserEntity user = userService.getUserById(userId);

        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }

}