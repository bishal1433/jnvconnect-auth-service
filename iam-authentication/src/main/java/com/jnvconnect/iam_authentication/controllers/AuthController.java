package com.jnvconnect.iam_authentication.controllers;

import com.jnvconnect.iam_authentication.dtos.UserDto;
import com.jnvconnect.iam_authentication.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        // Call the AuthService to handle registration logic
        // For now, just return the received userDto as a placeholder
        return ResponseEntity.ok(authService.register(userDto));
    }
}

