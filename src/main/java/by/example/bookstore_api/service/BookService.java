package by.example.bookstore_api.service;

import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface BookService {

    List<BookResponseDto> findAllSorted(String sortType);

    Page<BookResponseDto> findAll(int page, int size, String filter);

    BookResponseDto findById(UUID id);

    void save(BookRequestDto bookRequestDto);

    void update(UUID id, BookRequestDto bookRequestDto);

    void delete(UUID id);

    BookResponseDto findByTitle(String title);
}
