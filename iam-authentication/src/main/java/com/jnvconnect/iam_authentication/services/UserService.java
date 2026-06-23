package com.jnvconnect.iam_authentication.services;

import com.jnvconnect.iam_authentication.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, String userId);

    UserDto getUserByEmail(String email);

    void deleteUser(String userId);

    UserDto getUserById(String userId);

    List<UserDto> getAllUsers();
}
