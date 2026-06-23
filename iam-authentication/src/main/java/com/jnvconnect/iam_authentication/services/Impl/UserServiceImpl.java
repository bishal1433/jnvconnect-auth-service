package com.jnvconnect.iam_authentication.services.Impl;

import com.jnvconnect.iam_authentication.dtos.UserDto;
import com.jnvconnect.iam_authentication.entities.Provider;
import com.jnvconnect.iam_authentication.entities.User;
import com.jnvconnect.iam_authentication.exceptions.ResourceNotFoundException;
import com.jnvconnect.iam_authentication.repositories.UserRepository;
import com.jnvconnect.iam_authentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Validation Checks
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required!");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("User with given email already exists!");
        }

        User user = modelMapper.map(userDto, User.class);
        // Default local provider setting
        if (user.getProvider() == null) {
            user.setProvider(Provider.LOCAL);
        }

        // TODO: Assign Default Roles later in Security part

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given email: " + email));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        UUID uuid = UUID.fromString(userId);
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given ID: " + userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        UUID uuid = UUID.fromString(userId);
        User existingUser = userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given ID: " + userId));

        // Update fields (Email cannot be changed)
        if (userDto.getName() != null) existingUser.setName(userDto.getName());
        if (userDto.getPassword() != null) existingUser.setPassword(userDto.getPassword()); // TODO: Password hashing
        if (userDto.getImage() != null) existingUser.setImage(userDto.getImage());

        existingUser.setUpdatedAt(Instant.now());

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        UUID uuid = UUID.fromString(userId);
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given ID: " + userId));
        userRepository.delete(user);
    }
}