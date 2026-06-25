package com.jnvconnect.iam_authentication.services.Impl;

import com.jnvconnect.iam_authentication.dtos.UserDto;
import com.jnvconnect.iam_authentication.services.AuthService;
import com.jnvconnect.iam_authentication.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;


    @Override
    public UserDto register(UserDto userDto) {
        return userService.createUser(userDto);
    }
}
