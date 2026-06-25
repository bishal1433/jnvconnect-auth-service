package com.jnvconnect.iam_authentication.services;

import com.jnvconnect.iam_authentication.dtos.UserDto;

public interface AuthService {
    UserDto register(UserDto userDto);
    //login

}
