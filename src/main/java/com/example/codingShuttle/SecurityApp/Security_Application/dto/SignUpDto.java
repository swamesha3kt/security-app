package com.example.codingShuttle.SecurityApp.Security_Application.dto;

import com.example.codingShuttle.SecurityApp.Security_Application.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {

    private String email;
    private String password;
    private String name;

    private Set<Role> roles;
}
