package com.example.codingShuttle.SecurityApp.Security_Application.securityService;

import com.example.codingShuttle.SecurityApp.Security_Application.dto.SignUpDto;
import com.example.codingShuttle.SecurityApp.Security_Application.dto.UserDto;
import com.example.codingShuttle.SecurityApp.Security_Application.entities.UserEntity;
import com.example.codingShuttle.SecurityApp.Security_Application.exceptions.ResourceNotFoundException;
import com.example.codingShuttle.SecurityApp.Security_Application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User with email "+ username +" not found"));
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id "+ userId +
                " not found"));
    }

    public UserEntity getUsrByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    public UserDto signUp(SignUpDto signUpDto) {
        Optional<UserEntity> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()) {
            throw new BadCredentialsException("User with email already exits "+ signUpDto.getEmail());
        }

        UserEntity toBeCreatedUser = modelMapper.map(signUpDto, UserEntity.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        UserEntity savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public UserEntity save(UserEntity newUser) {
        return userRepository.save(newUser);
    }

}
