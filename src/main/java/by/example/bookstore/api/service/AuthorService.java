package by.example.bookstore.api.service;

import by.example.bookstore.api.model.dto.request.AuthorRequestDto;
import by.example.bookstore.api.model.dto.response.AuthorResponseDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

  AuthorResponseDto findById(UUID authorId);

  List<AuthorResponseDto> findAll(String filter);

  void save(AuthorRequestDto authorRequestDto);

  void deleteById(UUID authorId);

  void update(UUID authorId, AuthorRequestDto authorRequestDto);

}
