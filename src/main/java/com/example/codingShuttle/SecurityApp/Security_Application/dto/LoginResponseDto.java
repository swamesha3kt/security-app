package com.example.codingShuttle.SecurityApp.Security_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private Long id;
    private String accessToken;
    private String refreshToken;
}
