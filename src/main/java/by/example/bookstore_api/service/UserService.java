package by.example.bookstore_api.service;

import by.example.bookstore_api.model.dto.request.UserRequestDto;
import by.example.bookstore_api.model.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto findById(UUID userId);

    List<UserResponseDto> findAll();

    void save(UserRequestDto userRequestDto);

    void delete(UUID userId);

    void update(UUID userId, UserRequestDto userRequestDto);

    UserResponseDto findByUsername(String username);
}
