package by.example.bookstore_api.service;

import by.example.bookstore_api.model.dto.request.BookstoreRequestDto;
import by.example.bookstore_api.model.dto.response.BookstoreResponseDto;

import java.util.List;
import java.util.UUID;

public interface BookstoreService {

    BookstoreResponseDto findById(UUID id);

    List<BookstoreResponseDto> findAll();

    void save(BookstoreRequestDto bookstoreRequestDto);

    void delete(UUID bookstoreId);

    void update(UUID bookstoreId, BookstoreRequestDto bookstoreRequestDto);

    BookstoreResponseDto findByName(String name);
}
