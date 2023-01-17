package com.siit.hospital_manager.controller;

import com.siit.hospital_manager.model.User;
import com.siit.hospital_manager.model.dto.CreateUserDto;
import com.siit.hospital_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User createUser(CreateUserDto createUserDto) {

        User user = User.builder()
                .userName(createUserDto.getUserName())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .isActive(createUserDto.isActive())
                .roles(createUserDto.getRoles())
                .build();
        return userRepository.save(user);
    }
}
