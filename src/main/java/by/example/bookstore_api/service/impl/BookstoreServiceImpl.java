package by.example.bookstore_api.service.impl;

import by.example.bookstore_api.exception.BookstoreException;
import by.example.bookstore_api.mapper.BookStoreMapper;
import by.example.bookstore_api.model.dto.request.BookstoreRequestDto;
import by.example.bookstore_api.model.dto.response.BookstoreResponseDto;
import by.example.bookstore_api.model.entity.Bookstore;
import by.example.bookstore_api.repository.BookstoreRepository;
import by.example.bookstore_api.service.BookstoreService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookstoreServiceImpl implements BookstoreService {

    private final BookstoreRepository bookstoreRepository;
    private final BookStoreMapper bookStoreMapper;

    public BookstoreResponseDto findById(UUID bookstoreId) {
    return bookstoreRepository
        .findById(bookstoreId)
        .map(bookStoreMapper::toBookstoreDto)
        .orElseThrow(
            () ->
                new BookstoreException(
                    String.format("Bookstore with id %s not found", bookstoreId)));
    }

  public List<BookstoreResponseDto> findAll(String filter) {
        List<Bookstore> bookstores;
        if (filter != null && !filter.isEmpty()) {
            bookstores = bookstoreRepository.findByNameContainingIgnoreCase(filter);
        } else {
            bookstores = bookstoreRepository.findAll();
        }
        return bookStoreMapper.toBookstoresResponse(bookstores);
    }

    public void save(BookstoreRequestDto bookstoreRequestDto) {
        if (bookstoreRepository.existsByName(bookstoreRequestDto.name())) {
      throw new BookstoreException(
          "Bookstore with name " + bookstoreRequestDto.name() + " already exists");
        }
        bookstoreRepository.save(bookStoreMapper.toBookstore(bookstoreRequestDto));
    }

    public void delete(UUID bookstoreId) {
        bookstoreRepository.deleteById(bookstoreId);
    }

    @Transactional
    public void update(UUID bookstoreId, BookstoreRequestDto bookstoreRequestDto) {
    Bookstore bookstore =
        bookstoreRepository
            .findById(bookstoreId)
            .orElseThrow(
                () -> new BookstoreException("Bookstore with id " + bookstoreId + " not found"));

        bookstore.setName(bookstoreRequestDto.name());
        bookstoreRepository.save(bookstore);
    }

}
