package by.example.bookstore_api.service;

import by.example.bookstore_api.mapper.BookStoreMapper;
import by.example.bookstore_api.model.dto.request.BookstoreRequestDto;
import by.example.bookstore_api.model.dto.response.BookstoreResponseDto;
import by.example.bookstore_api.model.entity.Bookstore;
import by.example.bookstore_api.repository.BookstoreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookstoreService {

    private BookstoreRepository bookstoreRepository;
    private BookStoreMapper bookStoreMapper;

    public BookstoreResponseDto findById(UUID BookstoreId) {
        return bookstoreRepository.findById(BookstoreId)
                .map(bookStoreMapper::toBookstoreDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Bookstore with id %s not found", BookstoreId)));
    }

    public List<BookstoreResponseDto> findAll() {
       return bookStoreMapper.toBookstoresResponse(bookstoreRepository.findAll());
    }

    public void save(BookstoreRequestDto bookstoreRequestDto) {
        if (bookstoreRepository.existsByName(bookstoreRequestDto.name())) {
            throw new IllegalArgumentException("Bookstore with name " + bookstoreRequestDto.name() + " already exists");
        }
        bookstoreRepository.save(bookStoreMapper.toBookstore(bookstoreRequestDto));
    }

    public void delete(UUID BookstoreId) {
        bookstoreRepository.deleteById(BookstoreId);
    }

    @Transactional
    public void update(UUID bookstoreId, BookstoreRequestDto bookstoreRequestDto) {
        Bookstore bookstore = bookstoreRepository.findById(bookstoreId)
                .orElseThrow(() -> new EntityNotFoundException("Bookstore with id " + bookstoreId + " not found"));

        bookstore.setName(bookstoreRequestDto.name());
        bookstoreRepository.save(bookstore);
    }

    public BookstoreResponseDto findByName(String name) {
        return bookstoreRepository.findByName(name)
                .map(bookStoreMapper::toBookstoreDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Bookstore with name %s not found", name)));
    }
}
