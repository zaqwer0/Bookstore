package by.example.bookstore_api.service.impl;

import by.example.bookstore_api.config.exceptionHandler.CustomNotFoundException;
import by.example.bookstore_api.config.exceptionHandler.UserExists;
import by.example.bookstore_api.mapper.UserMapper;
import by.example.bookstore_api.model.dto.request.UserRequestDto;
import by.example.bookstore_api.model.dto.response.UserResponseDto;
import by.example.bookstore_api.model.entity.User;
import by.example.bookstore_api.repository.UserRepository;
import by.example.bookstore_api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto findById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", userId)));
    }

    public List<UserResponseDto> findAll() {
        return userMapper.toUserResponse(userRepository.findAll());
    }

    public void save(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.email())) {
            throw new UserExists(String.format("Username %s already exists", userRequestDto.username()));
        }
        userRepository.save(userMapper.toUser(userRequestDto));
    }

    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }

    public void update(UUID userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("User with id %s not found", userId))
                );

        user.setUsername(userRequestDto.username());
        user.setPassword(userRequestDto.password());
        user.setEmail(userRequestDto.email());
        userRepository.save(user);

    }

    public UserResponseDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with name %s not found", username)));
    }
}
