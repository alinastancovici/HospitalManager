package com.siit.hospital_manager.service;

import com.siit.hospital_manager.controller.UserService;
import com.siit.hospital_manager.model.User;
import com.siit.hospital_manager.model.dto.CreateUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping
    public User createUser(@Valid @RequestBody CreateUserDto createUserDto){
        return userService.createUser(createUserDto);
    }
}
